package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.SkillController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class SkillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        SkillController skillController = null;
        try {
            skillController = new SkillController();
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
                handleGetRequest(exchange, skillController, splittedPath);
                break;

            case "POST":
                handlePostRequest(exchange, skillController);
                break;

            case "PUT":
                handlePutRequest(exchange, skillController, splittedPath);
                break;

            case "DELETE":
                handleDeleteRequest(exchange, skillController, splittedPath);
                break;

            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGetRequest(HttpExchange exchange, SkillController skillController, String[] splittedPath) throws IOException {
        String response;
        try {
            if (splittedPath.length == 2) {
                // Case 1: Retrieve all skills
                response = skillController.getSkills();
                sendResponse(exchange, 200, response);
            } else if (splittedPath.length == 3 && splittedPath[2].matches("\\d+")) {
                // Case 2: Retrieve skills for a specific user (last parameter is user ID)
                int userId = Integer.parseInt(splittedPath[2]);
                response = skillController.getSkillsByUserId(userId);
                sendResponse(exchange, 200, response);
            } else {
                sendResponse(exchange, 400, "Bad Request: Invalid URL path");
            }
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Bad Request: Invalid ID");
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }
    
    

    private void handlePostRequest(HttpExchange exchange, SkillController skillController) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        requestBody.close();

        String newSkill = body.toString();
        JSONObject jsonObject = new JSONObject(newSkill);

        try {
            String response = skillController.createSkill(
                    jsonObject.getInt("id"),
                    jsonObject.getString("explaination"),
                    jsonObject.getInt("userId")
            );
            if (response.equals("user not found") || response.equals("you can only have 5 skills")) {
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

    private void handlePutRequest(HttpExchange exchange, SkillController skillController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String skillId = splittedPath[splittedPath.length - 1];
        int skillIdNum;
        try {
            skillIdNum = Integer.parseInt(skillId);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Skill ID");
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

        String updatedSkill = body.toString();
        JSONObject jsonObject = new JSONObject(updatedSkill);

        try {
            String response = skillController.updateSkill(
                    skillIdNum,
                    jsonObject.getString("explaination"),
                    jsonObject.getInt("userId")
            );
            if (response.equals("user not found")) {
                sendResponse(exchange, 404, response);
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

    private void handleDeleteRequest(HttpExchange exchange, SkillController skillController, String[] splittedPath) throws IOException {
        if (splittedPath.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        String skillId = splittedPath[splittedPath.length - 1];
        int skillIdNum;
        try {
            skillIdNum = Integer.parseInt(skillId);
        } catch (NumberFormatException e) {
            sendResponse(exchange, 400, "Invalid Skill ID");
            return;
        }

        try {
            skillController.deleteSkill(skillIdNum);
            sendResponse(exchange, 200, "Skill deleted successfully");
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
