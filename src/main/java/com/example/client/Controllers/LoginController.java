package main.java.com.example.client.Controllers;
import main.java.com.example.server.models.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;


import main.java.com.example.server.controllers.UserController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.com.example.client.Linkedin;
import main.java.com.example.server.controllers.UserController;
import javafx.stage.Stage;


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
    private Label wrongInputLabel;

    public void loginButtonClick(ActionEvent event) {
        String email = loginEmail.getText();
        String password = loginPassword.getText();

        if (UserController.isValidEmail(email) == false || UserController.isValidPassword(password) == false) {
            wrongInputLabel.setText("please enter correct format!");
        }   
        else {
            try {
                try {
                    URL url = new URL("http://localhost:8080/sessions/" + email + "/" + password);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputline;
                    StringBuffer response1 = new StringBuffer();
                    while ((inputline = in.readLine()) != null) {
                        response1.append(inputline);
                    }
                    in.close();
                    String response = response1.toString();

                    if (response.equals("Email or Password is incorrect")) {
                        wrongInputLabel.setText("Email or Password is incorrect");
                    }
                    else{
                        // this will be implemented after the completion of the linkedin.java

                    }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    public void signup(ActionEvent event) {
        // try {
        //     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //     Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Signup.fxml"));
        //     Scene scene = new Scene(root);

        //     stage.setScene(scene);
        //     stage.show();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
    
}
