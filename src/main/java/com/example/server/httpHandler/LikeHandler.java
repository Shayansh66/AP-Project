package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.LikeController;
import main.java.com.example.server.utils.JWTUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class LikeHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LikeController likeController = null;
        try {
            likeController = new LikeController();
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response = "";
        String[] splittedPath = path.split("/");

        switch (method) {
            case "POST":
                handlePostRequest(exchange, likeController);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, likeController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handlePostRequest(HttpExchange exchange, LikeController likeController) throws IOException {
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

        int userId = (int) claims.get("userId");

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

            if (likeController.isLiking(userId, likedPostId)) {
                likeController.createLike(id, userId, likedPostId);
                sendResponse(exchange, 200, "Like toggled successfully");
            } else {
                likeController.createLike(id, userId, likedPostId);
                sendResponse(exchange, 201, "Like created successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, LikeController likeController, String[] splittedPath) throws IOException {
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

        int userId = (int) claims.get("userId");

        try {
            likeController.deleteLikes();
            sendResponse(exchange, 200, "All likes deleted successfully");
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
