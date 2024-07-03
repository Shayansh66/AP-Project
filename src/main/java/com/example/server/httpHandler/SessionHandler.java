package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.UserController;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class SessionHandler implements HttpHandler {

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

        switch (method) {
            case "GET":
                handleGetMethod(exchange, userController);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetMethod(HttpExchange exchange, UserController userController) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String response = "";
        String[] splittedPath = path.split("/");
        if (splittedPath.length != 4) { // Ensure there are exactly 4 parts in the URL
            sendResponse(exchange, 400, "Bad Request: URL should be /session/{email}/{password}");
            return;
        }
        String email = splittedPath[2]; 
        String password = splittedPath[3]; 
        String result = null;
        try {
            result = userController.getUser(email, password); // Adjusted to use email
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        if (result == null) {
            response = "Email or Password is incorrect"; // Adjusted message for email
            sendResponse(exchange, 401, response);
        } else {
            response = "Welcome user with Email " + email + " !!!"; // Adjusted message for email
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
