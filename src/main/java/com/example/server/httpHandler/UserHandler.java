package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.UserController;

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

public class UserHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        UserController userController = null;
        try {
            userController = new UserController();
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
            case "GET":
                handleGetRequest(exchange, userController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, userController);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, userController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, UserController userController, String[] splittedPath) throws IOException {
        String response;
        try {
            if (splittedPath.length == 2) {
                response = userController.getUsers();
            } else {
                String userId = splittedPath[splittedPath.length - 1];
                try {
                    int userIdNum = Integer.parseInt(userId);
                    response = userController.getUserById(userIdNum);
                } catch (NumberFormatException e) {
                    response = userController.getUserByEmail(userId);
                }
            }
            sendResponse(exchange, 200, response);
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePostRequest(HttpExchange exchange, UserController userController) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newUser = body.toString();
        JSONObject jsonObject = new JSONObject(newUser);

        try {
            String response = userController.createUser(
                jsonObject.getInt("id"),
                jsonObject.getString("password"),
                jsonObject.getString("email"),
                jsonObject.getString("firstName"),
                jsonObject.getString("lastName"),
                jsonObject.optString("additionalName", ""),
                jsonObject.optString("headTitle", ""),
                jsonObject.optString("country", ""),
                jsonObject.optString("city", ""),
                jsonObject.optString("requiredJob", "")
            );
            if (response.equals("invalid email format") || response.equals("invalid password format")) {
                sendResponse(exchange, 400, response);
            } else {
                sendResponse(exchange, 201, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, UserController userController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String userId = splittedPath[splittedPath.length - 1];
        try {
            int userIdNum = Integer.parseInt(userId);
            String response = userController.deleteUser(userIdNum);
            if (response.equals("no user found with this id")) {
                sendResponse(exchange, 404, response);
            } else {
                sendResponse(exchange, 200, response);
            }
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid User ID");
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
