package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.UserController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;

public class UserHandler implements HttpHandler {
    @Override 
    public void handle (HttpExchange exchange ) throws IOException {
        UserController userController = null;
        try {
            userController = new UserController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String method =  exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] splittedpath = path.split("/");
        String response = "";


        switch (method) {
            case "GET":
                if (splittedpath.length == 2) {
                    try {
                        response = userController.getUsers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                
                else {
                    int userId = Integer.valueOf(splittedpath[splittedpath.length - 1]);

                    try {
                        response = userController.getUserById(userId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case "POST":
                InputStream requestBody = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
                StringBuilder body = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
                requestBody.close();
                reader.close();

                String newUser = body.toString();
                JSONObject jsonObject = new JSONObject(newUser);
                try {
                    response = userController.createUser(
                        jsonObject.getInt("id"),
                        jsonObject.getString("password"),
                        jsonObject.getString("email"),
                        jsonObject.getString("firstname"),
                        jsonObject.getString("lastname"),
                        jsonObject.getString("additionalname"),
                        jsonObject.getString("headTitle"),
                        jsonObject.getString("country"),
                        jsonObject.getString("city"),
                        jsonObject.getString("requiredJob")
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                case "DELETE":
                if (splittedpath.length > 2) {
                    int userId = Integer.valueOf(splittedpath[splittedpath.length - 1]);
                    try {
                        response = userController.deleteUser(userId);
                    }
                     catch (Exception e) {
                        throw new RuntimeException(e);
                    }
        }

    
    }

    exchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
        

}

}
