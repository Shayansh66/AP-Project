package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.PostController;
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

public class PostHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PostController postController = null;
        try {
            postController = new PostController();
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
                handleGetRequest(exchange, postController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, postController);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, postController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, PostController postController, String[] splittedPath) throws IOException {
        String response;
        try {
            if (splittedPath.length == 2) {
                response = postController.getPosts();
            } else {
                String postId = splittedPath[splittedPath.length - 1];
                int postIdNum = Integer.parseInt(postId);
                response = postController.getPost(postIdNum);
                if (response == null) {
                    response = "No post found with this ID";
                }
            }
            sendResponse(exchange, 200, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handlePostRequest(HttpExchange exchange, PostController postController) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newPost = body.toString();
        JSONObject jsonObject = new JSONObject(newPost);

        try {
            String response = postController.createPost(
                jsonObject.getInt("id"),
                jsonObject.getInt("writerId"),
                jsonObject.getString("content"),
                jsonObject.getInt("likenumbers"),
                jsonObject.getInt("commentnumbers"),
                new Timestamp(System.currentTimeMillis())
            );
            sendResponse(exchange, 201, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, PostController postController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String postId = splittedPath[splittedPath.length - 1];
        int postIdNum;
        try {
            postIdNum = Integer.parseInt(postId);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Post ID");
            return;
        }

        try {
            postController.deletepost(postIdNum);
            sendResponse(exchange, 200, "Post deleted successfully");
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
