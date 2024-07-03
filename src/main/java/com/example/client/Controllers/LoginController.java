package main.java.com.example.client.Controllers;
import main.java.com.example.server.models.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import main.java.com.example.server.controllers.UserController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.com.example.client.Linkedin;
import main.java.com.example.server.controllers.UserController;


public class LoginController {

    @FXML
    private TextField loginEmail;

    @FXML
    private TextField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signupHyperlink;
    
    @FXML
    Label wrong_pass_label;

    public void loginButtonClick(ActionEvent event) {
        String email = loginEmail.getText();
        String password = loginPassword.getText();

<<<<<<< HEAD
        if (email.length() == 0 || password.length() == 0)
        wrong_pass_label.setText("please enter all the fields");
        else {
            try {
                URL url = new URL("http://localhost:8080/sessions/" + email + "/" +password);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                BufferedReader in =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine ;
                StringBuffer response1 = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response1.append(inputLine);
                }
                in.close();
                String response = response1.toString();
                if (response.equals("Email or Password is incorrect")) {
                    wrong_pass_label.setText("Email or Password is incorrect");
                }
=======
        if (UserController.isValidEmail(email) == false || UserController.isValidPassword(password) == false) {

        }
>>>>>>> 10d1304c11e60724db118e8707b0780c506c6b64

                else {
                    Linkedin linkedin = new Linkedin();
                    url = new URL("http://localhost:8080/users/" + email);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    responseCode = connection.getResponseCode();
                     BufferedReader in1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputline1;
                    StringBuffer response2 = new StringBuffer();
                    while ((inputline1 = in1.readLine()) != null) {
                        response2.append(inputline1);
                    }
                    in.close();
                    response = response2.toString();
                    JSONObject jsonObject = new JSONObject(response);
                    User user = new User(jsonObject.getInt("id"),
                    jsonObject.getString("password"),
                    jsonObject.getString("email"),
                    jsonObject.getString("firstName"),
                    jsonObject.getString("lastName"),
                    jsonObject.optString("additionalName", ""),
                    jsonObject.optString("headTitle", ""),
                    jsonObject.optString("country", ""),
                    jsonObject.optString("city", ""),
                    jsonObject.optString("requiredJob", ""));
                    linkedin.log
                }
            } catch (ConnectException e) {
                wrong_pass_label.setText("connection failed");
            }
        }

    }

    public void signup(ActionEvent event) {
        System.out.println("signup");
    }
    
}
