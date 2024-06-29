package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.example.server.controllers.UserController;
import main.java.com.example.server.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserHandler implements HttpHandler {

    private final UserController userController;
    private final ObjectMapper objectMapper;

    public UserHandler() throws SQLException {
        this.userController = new UserController();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String response = "";
        int statusCode = 200;

        switch (requestMethod) {
            case "POST":
                handlePost(exchange);
                break;
            case "DELETE":
                handleDelete(exchange);
                break;
            case "GET":
                handleGet(exchange);
                break;
            default:
                response = "Method not supported";
                statusCode = 404;
                break;
        }

        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        String requestBodyString = new String(requestBody.readAllBytes());
        Map<String, String> parsedBody = parseRequestBody(requestBodyString);

        try {
            String result = userController.createUser(
                    Integer.parseInt(parsedBody.get("id")),
                    parsedBody.get("password"),
                    parsedBody.get("email"),
                    parsedBody.get("firstName"),
                    parsedBody.get("lastName"),
                    parsedBody.get("additionalName"),
                    parsedBody.get("headTitle"),
                    parsedBody.get("country"),
                    parsedBody.get("city"),
                    parsedBody.get("requiredJob")
            );
            sendResponse(exchange, result, 200);
        } catch (NumberFormatException | SQLException e) {
            sendResponse(exchange, "Error creating user", 500);
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        int id = Integer.parseInt(pathParts[pathParts.length - 1]);

        try {
            String result = userController.deleteUser(id);
            sendResponse(exchange, result, 200);
        } catch (SQLException e) {
            sendResponse(exchange, "Error deleting user", 500);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        String result = "";

        if (pathParts.length > 2) {
            // Get user by ID
            int id = Integer.parseInt(pathParts[pathParts.length - 1]);
            try {
                result = userController.getUserById(id);
            } catch (SQLException | JsonProcessingException e) {
                result = "Error fetching user";
            }
        } else {
            // Get all users
            try {
                result = userController.getUsers();
            } catch (SQLException | JsonProcessingException e) {
                result = "Error fetching users";
            }
        }

        sendResponse(exchange, result, 200);
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseRequestBody(String requestBody) {
        Map<String, String> parsedBody = new HashMap<>();
        String[] pairs = requestBody.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                parsedBody.put(keyValue[0], keyValue[1]);
            }
        }

        return parsedBody;
    }
}
