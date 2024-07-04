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

        /*
                    ----------------------------------------------------------------------------------
                    Here is where a login request must be created to server with 'email' and 'password'
         */

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
