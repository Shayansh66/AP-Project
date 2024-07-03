package main.java.com.example.server.httpHandler;
import main.java.com.example.server.controllers.MessageController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class MessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        MessageController messageController = null;
        try {
            messageController = new MessageController();
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        switch (method) {
            case "GET":
                handleGetRequest(exchange, messageController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, messageController);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, messageController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, MessageController messageController, String[] splittedPath) throws IOException {
        try {
            String response;
            if (splittedPath.length != 4) {
                sendResponse(exchange, 400, "Bad Request: User ID not provided");
                return;
            }

            // Assuming splittedPath[2] contains "received" or "sent"
            String messageType = splittedPath[2];
            int userId;
            try {
                userId = Integer.parseInt(splittedPath[3]);
            } catch (NumberFormatException e) {
                sendResponse(exchange, 400, "Bad Request: Invalid User ID");
                return;
            }

            if (messageType.equals("received")) {
                response = messageController.getRecievedMessages(userId);
            } else if (messageType.equals("sent")) {
                response = messageController.getSentMessages(userId);
            } else {
                sendResponse(exchange, 400, "Bad Request: Invalid Message Type");
                return;
            }

            sendResponse(exchange, 200, response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePostRequest(HttpExchange exchange, MessageController messageController) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newMessage = body.toString();
        JSONObject jsonObject = new JSONObject(newMessage);

        try {
            int id = jsonObject.getInt("id");
            int senderId = jsonObject.getInt("senderId");
            int receiverId = jsonObject.getInt("receiverId");
            String context = jsonObject.getString("context");
            String[] multimedia = jsonObject.has("multiMedia") ? jsonObject.getJSONArray("multiMedia").toList().toArray(new String[0]) : new String[0];

            String response = messageController.createMessage(id, senderId, receiverId, context, multimedia);
            sendResponse(exchange, 201, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, MessageController messageController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 4) {
            sendResponse(exchange, 400, "Bad Request: User ID not provided");
            return;
        }

        int messageId;
        try {
            messageId = Integer.parseInt(splittedPath[3]);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Message ID");
            return;
        }

        try {
            String response = messageController.deleteMessage(messageId);
            if (response.equals("message not found with this id")) {
                sendResponse(exchange, 404, response);
            } else {
                sendResponse(exchange, 200, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
