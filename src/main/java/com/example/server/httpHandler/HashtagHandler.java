package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.HashtagController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class HashtagHandler implements HttpHandler {

    private final HashtagController hashtagController;

    public HashtagHandler() throws SQLException {
        hashtagController = new HashtagController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        switch (method) {
            case "POST":
                handlePostRequest(exchange);
                break;
            case "DELETE":
                handleDeleteRequest(exchange);
                break;
            case "GET":
                handleGetRequest(exchange);
                break;
            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");
        int postId = Integer.parseInt(splittedPath[splittedPath.length - 1]);

        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String content = body.toString();

        try {
            String response = hashtagController.createHashtag(postId, content);
            sendResponse(exchange, 200, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");
        String content = splittedPath[splittedPath.length - 1];

        try {
            String response = hashtagController.deleteHashtag(content);
            sendResponse(exchange, 200, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handleGetRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        int postId = Integer.parseInt(splittedPath[2]);

        try {
            String response = hashtagController.getpostHashtags(postId);
            sendResponse(exchange, 200, response);
        } catch (SQLException | JsonProcessingException e) {
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
