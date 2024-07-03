package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.FollowController;
import org.json.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class FollowHandler implements HttpHandler {

    private final FollowController followController;

    public FollowHandler() throws SQLException {
        followController = new FollowController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        switch (method) {
            case "GET":
                handleGetRequest(exchange, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, String[] splittedPath) throws IOException {
        try {
            String response;
            if (splittedPath.length == 3 && splittedPath[2].equals("follows")) {
                response = followController.getFollows();
            } else if (splittedPath.length == 4 && splittedPath[2].equals("followers")) {
                int userId = Integer.parseInt(splittedPath[3]);
                response = followController.getFollowers(userId);
            } else if (splittedPath.length == 4 && splittedPath[2].equals("followings")) {
                int userId = Integer.parseInt(splittedPath[3]);
                response = followController.getFollowings(userId);
            } else {
                sendResponse(exchange, 404, "Not Found");
                return;
            }
            sendResponse(exchange, 200, response);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid ID");
        } catch (SQLException | IOException e) {
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

        String newFollow = body.toString();
        JSONObject jsonObject = new JSONObject(newFollow);

        try {
            int id = jsonObject.getInt("id");
            int followerId = jsonObject.getInt("followerId");
            int followingId = jsonObject.getInt("followingId");

            followController.createFollow(id, followerId, followingId);
            sendResponse(exchange, 201, "Follow created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String followIdStr = splittedPath[2];
        int followId;
        try {
            followId = Integer.parseInt(followIdStr);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Follow ID");
            return;
        }

        try {
            followController.deleteFollow(followId);
            sendResponse(exchange, 200, "Follow deleted successfully");
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
