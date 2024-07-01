package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.FollowController;
import main.java.com.example.server.utils.JWTUtils;

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
import java.util.Map;

public class FollowHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        FollowController followController;
        try {
            followController = new FollowController();
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
                handleGetRequest(exchange, followController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, followController);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, followController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, FollowController followController, String[] splittedPath) throws IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendResponse(exchange, 401, "Unauthorized");
            return;
        }

        String jwtToken = authHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JWTUtils.verifyJWT(jwtToken);
        } catch (Exception e) {
            sendResponse(exchange, 401, "Invalid JWT Token");
            return;
        }

        try {
            String response;
            if (splittedPath.length == 2) {
                response = followController.getFollows();
            } else if (splittedPath.length == 3) {
                if (splittedPath[2].equals("followers")) {
                    int followingId = (int) claims.get("userId");
                    response = followController.getFollowers(followingId);
                } else if (splittedPath[2].equals("followings")) {
                    int followerId = (int) claims.get("userId");
                    response = followController.getFollowings(followerId);
                } else {
                    int followId = Integer.parseInt(splittedPath[2]);
                    response = followController.getFollow(followId);
                }
            } else {
                sendResponse(exchange, 400, "Bad Request");
                return;
            }
            sendResponse(exchange, 200, response);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid ID");
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePostRequest(HttpExchange exchange, FollowController followController) throws IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendResponse(exchange, 401, "Unauthorized");
            return;
        }

        String jwtToken = authHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JWTUtils.verifyJWT(jwtToken);
        } catch (Exception e) {
            sendResponse(exchange, 401, "Invalid JWT Token");
            return;
        }

        int followerId = (int) claims.get("userId");

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
            int followingId = jsonObject.getInt("followingId");
            boolean isConnection = jsonObject.getBoolean("isConnection");

            followController.createFollow(id, followerId, followingId, isConnection);
            sendResponse(exchange, 201, "Follow created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, FollowController followController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendResponse(exchange, 401, "Unauthorized");
            return;
        }

        String jwtToken = authHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JWTUtils.verifyJWT(jwtToken);
        } catch (Exception e) {
            sendResponse(exchange, 401, "Invalid JWT Token");
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
