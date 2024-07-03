package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.ConnectController;
import main.java.com.example.server.utils.JWTUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ConnectController connectController;
        try {
            connectController = new ConnectController();
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        switch (method) {
            case "POST":
                handlePostRequest(exchange, connectController);
                break;
            case "DELETE":
                handleDeleteRequest(exchange, connectController, splittedPath);
                break;
            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handlePostRequest(HttpExchange exchange, ConnectController connectController) throws IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendResponse(exchange, 401, "Unauthorized");
            return;
        }

        String jwtToken = authHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JWTUtils.decodeJWT(jwtToken);
        } catch (Exception e) {
            sendResponse(exchange, 401, "Invalid JWT Token");
            return;
        }

        int userid1 = (int) claims.get("userId");

        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newConnection = body.toString();
        JSONObject jsonObject = new JSONObject(newConnection);

        try {
            int userid2 = jsonObject.getInt("userid2");
            connectController.createConnection(userid1, userid2);
            sendResponse(exchange, 201, "Connection created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, ConnectController connectController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 4) { // /connect/DELETE/userid1/userid2
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendResponse(exchange, 401, "Unauthorized");
            return;
        }

        String jwtToken = authHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JWTUtils.decodeJWT(jwtToken);
        } catch (Exception e) {
            sendResponse(exchange, 401, "Invalid JWT Token");
            return;
        }

        int userid1 = (int) claims.get("userId");
        int userid2;
        try {
            userid2 = Integer.parseInt(splittedPath[splittedPath.length - 1]);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid User ID");
            return;
        }

        try {
            connectController.deleteConnection(userid1, userid2);
            sendResponse(exchange, 200, "Connection deleted successfully");
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
