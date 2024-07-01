package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.UserController;
import main.java.com.example.server.utils.JWTUtils;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SessionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        UserController userController;
        try {
            userController = new UserController();
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String response;
        String[] splittedPath = path.split("/");

        if (!"GET".equals(method)) {
            sendResponse(exchange, 405, "Method Not Allowed");
            return;
        }

        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request: Expected format /session/{email}/{password}");
            return;
        }

        String email = splittedPath[1];
        String password = splittedPath[2];
        String result;
        try {
            result = userController.getUser(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        if (result == null) {
            response = "Email or Password is incorrect";
            sendResponse(exchange, 401, response);
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", email);
            String jwtToken = JWTUtils.createJWT(claims, 3600000); // 1 hour expiration

            Headers headers = exchange.getResponseHeaders();
            headers.add("Authorization", "Bearer " + jwtToken);
            response = "Welcome " + email + " !!!";
            sendResponse(exchange, 200, response);
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
