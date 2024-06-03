package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.example.server.controllers.UserController;
import main.java.com.example.server.utils.JwtAuth;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserHandler implements HttpHandler {
    private final UserController userController;
    private final ObjectMapper objectMapper;

    public UserHandler() throws SQLException {
        userController = new UserController();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response;
        int statusCode = 200;
        JwtAuth jwtAuth = new JwtAuth();

        // Check JWT authentication
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            statusCode = 401;
            response = "Unauthorized";
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Claims claims;
        try {
            claims = jwtAuth.verifyToken(token);
        } catch (Exception e) {
            statusCode = 401;
            response = "Invalid token";
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }

        // Access claims
        String userId = claims.getSubject();
        System.out.println("Authenticated user ID: " + userId);

        try {
            switch (method) {
                case "POST":
                    response = handleCreateUser(exchange);
                    break;
                case "DELETE":
                    response = handleDeleteUser(exchange);
                    break;
                case "GET":
                    response = handleGetUser(exchange);
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

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String handleCreateUser(HttpExchange exchange) throws IOException, SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        int id = Integer.parseInt(params.get("id"));
        String password = params.get("password");
        String email = params.get("email");
        String firstName = params.get("firstName");
        String lastName = params.get("lastName");
        String additionalName = params.get("additionalName");
        String headTitle = params.get("headTitle");
        String country = params.get("country");
        String city = params.get("city");
        String requiredJob = params.get("requiredJob");

        userController.createUser(id, password, email, firstName, lastName, additionalName, headTitle, country, city, requiredJob);
        return "User created successfully";
    }



  

    private String handleDeleteUser(HttpExchange exchange) throws SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        int id = Integer.parseInt(params.get("id"));
        userController.deleteUser(id);
        return "User deleted successfully";
    }

    private String handleGetUser(HttpExchange exchange) throws IOException, SQLException {
        Map<String, String> params = parseQueryParameters(exchange);
        String idParam = params.get("id");
        String emailParam = params.get("email");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            return userController.getUserById(id);
        } else if (emailParam != null) {
            return userController.getUserByEmail(emailParam);
        } else {
            return userController.getUsers();
        }
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
