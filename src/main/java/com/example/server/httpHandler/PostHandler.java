package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.example.server.controllers.PostController;
import main.java.com.example.server.utils.JwtAuth;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class PostHandler implements HttpHandler {
    private final PostController postController;
    private final ObjectMapper objectMapper;

    public PostHandler() throws SQLException {
        postController = new PostController();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;
        int statusCode = 200;
        JwtAuth jwtAuth = new JwtAuth();

        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            statusCode = 401;
            response = "Unauthorized";
            sendResponse(exchange, statusCode, response);
            return;
        }

        String token = authHeader.substring(7);
        Claims claims;
        try {
            claims = jwtAuth.verifyToken(token);
        } catch (Exception e) {
            statusCode = 401;
            response = "Invalid token";
            sendResponse(exchange, statusCode, response);
            return;
        }

        String userId = claims.getSubject();
        System.out.println("Authenticated user ID: " + userId);

        try {
            switch (method) {
                case "POST":
                    response = handleCreatePost(exchange);
                    break;
                case "DELETE":
                    response = handleDeletePost(exchange);
                    break;
                case "GET":
                    response = handleGetPost(exchange);
                    break;
                case "PUT":
                    response = handleUpdatePost(exchange);
                    break;
                default:
                    response = "Method Not Allowed";
                    statusCode = 405;
                    break;
            }
        } catch (SQLException e) {
            response = "Internal Server Error";
            statusCode = 500;
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            response = e.getMessage();
            statusCode = 400;
        }

        sendResponse(exchange, statusCode, response);
    }

    private String handleCreatePost(HttpExchange exchange) throws IOException, SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        int id = Integer.parseInt(params.get("id"));
        int writerId = Integer.parseInt(params.get("writerId"));
        String content = params.get("content");
        int likeNumbers = Integer.parseInt(params.get("likeNumbers"));
        int commentNumbers = Integer.parseInt(params.get("commentNumbers"));
        Timestamp createDate = Timestamp.valueOf(params.get("createDate"));
        int relatedGroupId = Integer.parseInt(params.get("relatedGroupId"));

        postController.createPost(id, writerId, content, likeNumbers, commentNumbers, createDate, relatedGroupId);
        return "Post created successfully";
    }

    private String handleDeletePost(HttpExchange exchange) throws SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        int id = Integer.parseInt(params.get("id"));
        postController.deletepost(id);
        return "Post deleted successfully";
    }

    private String handleGetPost(HttpExchange exchange) throws IOException, SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        String idParam = params.get("id");
        String writerIdParam = params.get("writerId");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            return postController.getPost(id);
        } else if (writerIdParam != null) {
            int writerId = Integer.parseInt(writerIdParam);
            return postController.getPostsbyWriterId(writerId);
        } else {
            return postController.getPosts();
        }
    }

    private String handleUpdatePost(HttpExchange exchange) throws IOException, SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        int id = Integer.parseInt(params.get("id"));
        int writerId = Integer.parseInt(params.get("writerId"));
        String content = params.get("content");
        int likeNumbers = Integer.parseInt(params.get("likeNumbers"));
        int commentNumbers = Integer.parseInt(params.get("commentNumbers"));
        Timestamp createDate = Timestamp.valueOf(params.get("createDate"));
        int relatedGroupId = Integer.parseInt(params.get("relatedGroupId"));

        postController.updatePost(id, writerId, content, likeNumbers, commentNumbers, createDate, relatedGroupId);
        return "Post updated successfully";
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseQueryParameters(HttpExchange exchange) {
        Map<String, String> parameters = new HashMap<>();
        String query = exchange.getRequestURI().getQuery();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    parameters.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return parameters;
    }
}
