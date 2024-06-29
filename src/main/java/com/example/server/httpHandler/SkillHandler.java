package main.java.com.example.server.httpHandler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.jsonwebtoken.JwtException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map; 
import main.java.com.example.server.controllers.SkillController;
import main.java.com.example.server.utils.JwtUtils;
import main.java.com.example.server.utils.HttpUtils;
public class SkillHandler implements HttpHandler {

    private final SkillController skillController;

    public SkillHandler() throws SQLException {
        this.skillController = new SkillController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String response = "";
        int statusCode = 200;

        try {
            switch (requestMethod) {
                case "POST":
                    handlePost(exchange);
                    break;
                case "PUT":
                    handlePut(exchange);
                    break;
                case "GET":
                    handleGet(exchange);
                    break;
                case "DELETE":
                    handleDelete(exchange);
                    break;
                default:
                    response = "Method not allowed";
                    statusCode = 405;
                    break;
            }
        } catch (Exception e) {
            response = "Internal server error";
            statusCode = 500;
            e.printStackTrace(); // Log the exception for debugging
        }

        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handlePost(HttpExchange exchange) throws IOException,SQLException {
        try {
            // Validate JWT token from request
            String jwtToken = extractJwtToken(exchange);
            if (jwtToken == null || !JwtUtils.validateJwtToken(jwtToken)) {
                sendResponse(exchange, "Unauthorized", 401);
                return;
            }

            // Extract userId from JWT token
            int userId = JwtUtils.extractUserIdFromJwtToken(jwtToken);

            // Process request
            Map<String, String> params = parseQueryString(exchange.getRequestURI().getQuery());
            String result = skillController.createSkill(
                    Integer.parseInt(params.get("id")),
                    params.get("explanation"),
                    userId
            );
            sendResponse(exchange, result, 200);
        } catch (NumberFormatException | IOException | JwtException e) {
            sendResponse(exchange, "Error processing request", 500);
        }
    }

    private void handlePut(HttpExchange exchange) throws IOException , SQLException {
        try {
            // Validate JWT token from request
            String jwtToken = extractJwtToken(exchange);
            if (jwtToken == null || !JwtUtils.validateJwtToken(jwtToken)) {
                sendResponse(exchange, "Unauthorized", 401);
                return;
            }

            // Extract userId from JWT token
            int userId = JwtUtils.extractUserIdFromJwtToken(jwtToken);

            // Process request
            Map<String, String> params = parseQueryString(exchange.getRequestURI().getQuery());
            String result = skillController.updateSkill(
                    Integer.parseInt(params.get("id")),
                    params.get("explanation"),
                    userId
            );
            sendResponse(exchange, result, 200);
        } catch (NumberFormatException | IOException | JwtException e) {
            sendResponse(exchange, "Error processing request", 500);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException , SQLException {
        try {
            // Validate JWT token from request
            String jwtToken = extractJwtToken(exchange);
            if (jwtToken == null || !JwtUtils.validateJwtToken(jwtToken)) {
                sendResponse(exchange, "Unauthorized", 401);
                return;
            }

            // Extract userId from JWT token
            int userId = JwtUtils.extractUserIdFromJwtToken(jwtToken);

            // Process request
            Map<String, String> params = parseQueryString(exchange.getRequestURI().getQuery());
            String result = skillController.getSkill(
                    Integer.parseInt(params.get("id")),
                    userId
            );
            sendResponse(exchange, result, 200);
        } catch (NumberFormatException | IOException | JwtException e) {
            sendResponse(exchange, "Error processing request", 500);
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException,SQLException {
        try {
            // Validate JWT token from request
            String jwtToken = extractJwtToken(exchange);
            if (jwtToken == null || !JwtUtils.validateJwtToken(jwtToken)) {
                sendResponse(exchange, "Unauthorized", 401);
                return;
            }

            // Extract userId from JWT token
            int userId = JwtUtils.extractUserIdFromJwtToken(jwtToken);

            // Process request
            Map<String, String> params = parseQueryString(exchange.getRequestURI().getQuery());
            skillController.deleteSkill(
                    Integer.parseInt(params.get("id")));
            sendResponse(exchange, "Skill deleted successfully", 200);
        } catch (NumberFormatException | IOException | JwtException e) {
            sendResponse(exchange, "Error processing request", 500);
        }
    }

    private String extractJwtToken(HttpExchange exchange) {
        // Extract JWT token from Authorization header
        String authorizationHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " length is 7
        }
        return null;
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

      public static Map<String, String> parseQueryString(String query) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                params.put(key, value);
            }
        }
        return params;
    }
    
}


