package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.example.server.controllers.ContactController;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContactHandler implements HttpHandler {

    private final ContactController contactController;

    public ContactHandler() throws SQLException {
        contactController = new ContactController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedPath = path.split("/");

        switch (method) {
            case "GET":
                handleGetRequest(exchange, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange);
                break;

            case "PUT":
                handlePutRequest(exchange, splittedPath);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, String[] splittedPath) throws IOException {
        try {
            String response;
            if (splittedPath.length == 2) {
                response = contactController.getContacts();
            } else if (splittedPath.length == 3) {
                int userId = Integer.parseInt(splittedPath[2]);
                response = contactController.getContactsByUserId(userId);
            } else {
                sendResponse(exchange, 400, "Bad Request");
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

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        JSONObject jsonObject = new JSONObject(body.toString());
        try {
            int id = jsonObject.getInt("id");
            int userId = jsonObject.getInt("userId");
            String profileLink = jsonObject.getString("profileLink");
            String email = jsonObject.getString("email");
            String phoneNumber = jsonObject.getString("phoneNumber");
            String phoneType = jsonObject.getString("phoneType");
            String address = jsonObject.getString("address");
            Timestamp birthday = Timestamp.valueOf(jsonObject.getString("birthday"));
            String birthdayVisibility = jsonObject.getString("birthdayVisibility");
            String communicationId = jsonObject.getString("communicationId");

            String response = contactController.createContact(id, userId, profileLink, email, phoneNumber, phoneType,
                    address, birthday, birthdayVisibility, communicationId);
            
            // Print SQL statement for debugging
            System.out.println("SQL Statement: " + response);

            sendResponse(exchange, 201, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handlePutRequest(HttpExchange exchange, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
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

        JSONObject jsonObject = new JSONObject(body.toString());
        try {
            int id = Integer.parseInt(splittedPath[2]);
            int userId = jsonObject.getInt("userId");
            String profileLink = jsonObject.getString("profileLink");
            String email = jsonObject.getString("email");
            String phoneNumber = jsonObject.getString("phoneNumber");
            String phoneType = jsonObject.getString("phoneType");
            String address = jsonObject.getString("address");
            Timestamp birthday = Timestamp.valueOf(jsonObject.getString("birthday"));
            String birthdayVisibility = jsonObject.getString("birthdayVisibility");
            String communicationId = jsonObject.getString("communicationId");

            String response = contactController.updateContact(id, userId, profileLink, email, phoneNumber, phoneType,
                    address, birthday, birthdayVisibility, communicationId);
            
            // Print SQL statement for debugging
            System.out.println("SQL Statement: " + response);

            sendResponse(exchange, 200, response);
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String contactIdStr = splittedPath[2];
        int contactId;
        try {
            contactId = Integer.parseInt(contactIdStr);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Contact ID");
            return;
        }

        try {
            contactController.deleteContact(contactId);
            sendResponse(exchange, 200, "Contact deleted successfully");
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
