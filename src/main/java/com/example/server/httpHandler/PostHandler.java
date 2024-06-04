package main.java.com.example.server.httpHandler;

import main.java.com.example.server.controllers.UserController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;

public class PostHandler implements HttpHandler {
    @Override 
    public void handle (HttpExchange exchange ) throws IOException {
        UserController userController = null;
        try {
            userController = new UserController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String method exchange.getRequestMethod();
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
                while ((line  = reader.readLine()) != null) {
                    body.append(line);
                }
                requestBody.close();
                String newUser = body.toString();
                JSONObject jsonobject = new JSONObject(newUser);
                try {
                    response = userController.createUser(jsonobject.getInt("id") , jsonobject.getString("password") , jsonobject.getString("email") , jsonobject.getString("firstname") , jsonobject.getString("lastname") , jsonobject.getString("additionalname") , jsonobject.getString("headTitle") , jsonobject.getString("country") , jsonobject.getString("city"), jsonobject.getString("requiredJob"));
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                case "DELTE":
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
