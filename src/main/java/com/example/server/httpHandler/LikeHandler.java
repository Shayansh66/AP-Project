package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.LikeController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class LikeHandler implements HttpHandler {

    private final LikeController likeController;

    public LikeHandler() throws SQLException {
        likeController = new LikeController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        switch (method) {
            case "GET":
                if (splittedPath.length < 2) {
                    sendResponse(exchange, 400, "Bad Request: Missing path parameter");
                    return;
                }
                String type = splittedPath[1];
                if ("likers".equals(type) && splittedPath.length == 3) {
                    handleGetLikers(exchange, splittedPath[2]);
                } else if ("likings".equals(type) && splittedPath.length == 3) {
                    handleGetLikings(exchange, splittedPath[2]);
                } else {
                    sendResponse(exchange, 404, "Not Found");
                }
                break;

            case "POST":
                handlePostRequest(exchange);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetLikers(HttpExchange exchange, String postIdStr) throws IOException {
        int postId;
        try {
            postId = Integer.parseInt(postIdStr);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid Post ID");
            return;
        }

        try {
            String response = likeController.getlikesByPostId(postId);
            sendResponse(exchange, 200, response);
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handleGetLikings(HttpExchange exchange, String userIdStr) throws IOException {
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid User ID");
            return;
        }

        try {
            String response = likeController.getlikesByUserId(userId);
            sendResponse(exchange, 200, response);
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newLike = body.toString();
        JSONObject jsonObject = new JSONObject(newLike);

        try {
            int id = jsonObject.getInt("id");
            int likedPostId = jsonObject.getInt("likedPostId");
            int likerId = jsonObject.getInt("likerId");

            String response = likeController.createLike(id, likerId, likedPostId);
            sendResponse(exchange, 200, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
