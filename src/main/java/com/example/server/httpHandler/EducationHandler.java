package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.example.server.controllers.EducationController;
import main.java.com.example.server.utils.JWTUtils;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class EducationHandler implements HttpHandler {

    private final EducationController educationController;
    private final ObjectMapper objectMapper;

    public EducationHandler() throws SQLException {
        this.educationController = new EducationController();
        this.objectMapper = new ObjectMapper();
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
        String response;
        try {
            if (splittedPath.length == 2) {
                int userId = getUserIdFromHeader(exchange);
                if (userId == -1) {
                    sendResponse(exchange, 400, "Bad Request: userId header is required for this request");
                    return;
                }
                response = educationController.getEducationByUserId(userId);
            } else {
                int educationId = Integer.parseInt(splittedPath[splittedPath.length - 1]);
                response = educationController.getEducationById(educationId);
                if (response == null) {
                    response = "No education found with this ID";
                }
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
        try {
            int userId = getUserIdFromHeader(exchange);
            if (userId == -1) {
                sendResponse(exchange, 400, "Bad Request: userId header is required for this request");
                return;
            }

            JSONObject jsonObject = getJSONObjectFromRequest(exchange);
            int id = jsonObject.getInt("id");
            String institutionName = jsonObject.getString("institutionName");
            String studyField = jsonObject.getString("studyField");
            Timestamp startDate = Timestamp.valueOf(jsonObject.getString("startDate"));
            Timestamp endDate = Timestamp.valueOf(jsonObject.getString("endDate"));
            float grade = (float) jsonObject.getDouble("grade");
            String descriptionOfActivities = jsonObject.getString("descriptionOfActivities");
            String description = jsonObject.getString("description");
            boolean notifyChanges = jsonObject.getBoolean("notifyChanges");

            educationController.createEducation(id, userId, institutionName, studyField, startDate, endDate, grade, descriptionOfActivities, description, notifyChanges);

            sendResponse(exchange, 201, "Education created successfully");
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

        try {
            int userId = getUserIdFromHeader(exchange);
            if (userId == -1) {
                sendResponse(exchange, 400, "Bad Request: userId header is required for this request");
                return;
            }

            JSONObject jsonObject = getJSONObjectFromRequest(exchange);
            int educationId = Integer.parseInt(splittedPath[2]);
            int id = jsonObject.getInt("id");
            String institutionName = jsonObject.getString("institutionName");
            String studyField = jsonObject.getString("studyField");
            Timestamp startDate = Timestamp.valueOf(jsonObject.getString("startDate"));
            Timestamp endDate = Timestamp.valueOf(jsonObject.getString("endDate"));
            float grade = (float) jsonObject.getDouble("grade");
            String descriptionOfActivities = jsonObject.getString("descriptionOfActivities");
            String description = jsonObject.getString("description");
            boolean notifyChanges = jsonObject.getBoolean("notifyChanges");

            educationController.updateEducation(educationId, userId, institutionName, studyField, startDate, endDate, grade, descriptionOfActivities, description, notifyChanges);

            sendResponse(exchange, 200, "Education updated successfully");
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid ID");
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

        try {
            int userId = getUserIdFromHeader(exchange);
            if (userId == -1) {
                sendResponse(exchange, 400, "Bad Request: userId header is required for this request");
                return;
            }

            int educationId = Integer.parseInt(splittedPath[2]);
            educationController.deleteEducation(educationId);

            sendResponse(exchange, 200, "Education deleted successfully");
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid ID");
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 400, "Bad Request");
        }
    }

    private int getUserIdFromHeader(HttpExchange exchange) {
        String userIdStr = exchange.getRequestHeaders().getFirst("userId");
        if (userIdStr == null) {
            return -1;
        }
        return Integer.parseInt(userIdStr);
    }

    private JSONObject getJSONObjectFromRequest(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();
        return new JSONObject(body.toString());
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
