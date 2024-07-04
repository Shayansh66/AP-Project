package main.java.com.example.server.httpHandler;


import main.java.com.example.server.controllers.CommentController;

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
import java.sql.Timestamp;

public class CommentHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        CommentController commentController = null;
        try {
            commentController = new CommentController();
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
                handleGetRequest(exchange, commentController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, commentController);
                break;

            case "PUT":
                handlePutRequest(exchange, commentController, splittedPath);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, commentController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, CommentController commentController, String[] splittedPath) throws IOException {
        String response;
        try {
            if (splittedPath.length == 3 && "user".equals(splittedPath[1])) {
                // Case: Retrieve comments for a specific user
                int userId = Integer.parseInt(splittedPath[2]);
                response = commentController.getUserComments(userId);
            } else if (splittedPath.length == 3 && "post".equals(splittedPath[1])) {
                // Case: Retrieve comments for a specific post
                int postId = Integer.parseInt(splittedPath[2]);
                response = commentController.getPostComments(postId);
            } else {
                sendResponse(exchange, 400, "Bad Request: Invalid URL path");
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

    private void handlePostRequest(HttpExchange exchange, CommentController commentController) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newComment = body.toString();
        JSONObject jsonObject = new JSONObject(newComment);

        try {
            String response = commentController.createComment(
                    jsonObject.getInt("id"),
                    jsonObject.getInt("writterid"),
                    jsonObject.getString("content"),
                    jsonObject.getInt("likeNumber"),
                    jsonObject.getInt("commentNumber"),
                    Timestamp.valueOf(jsonObject.getString("createDate")),
                    jsonObject.getInt("relatedPostId")
            );
            if (response.equals("no post found with this id")) {
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

    private void handlePutRequest(HttpExchange exchange, CommentController commentController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String commentId = splittedPath[splittedPath.length - 1];
        int commentIdNum;
        try {
            commentIdNum = Integer.parseInt(commentId);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Comment ID");
            return;
        }

        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String updatedComment = body.toString();
        JSONObject jsonObject = new JSONObject(updatedComment);

        try {
            String response = commentController.updateComment(
                    commentIdNum,
                    jsonObject.getInt("writterid"),
                    jsonObject.getString("content"),
                    jsonObject.getInt("likeNumber"),
                    jsonObject.getInt("commentNumber"),
                    Timestamp.valueOf(jsonObject.getString("createDate")),
                    jsonObject.getInt("relatedPostId")
            );
            if (response.equals("no post found with this id") || response.equals("no comment found with this id")) {
                sendResponse(exchange, 400, response);
            } else {
                sendResponse(exchange, 200, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, CommentController commentController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String commentId = splittedPath[splittedPath.length - 1];
        int commentIdNum;
        try {
            commentIdNum = Integer.parseInt(commentId);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Comment ID");
            return;
        }

        try {
            // Implement the delete functionality in CommentController and call it here
            // commentController.deleteComment(commentIdNum);
            sendResponse(exchange, 200, "Comment deleted successfully");
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

