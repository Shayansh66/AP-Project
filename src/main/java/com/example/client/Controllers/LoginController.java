package main.java.com.example.client.Controllers;

// import main.java.com.example.server.controllers.UserController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private TextField loginEmail;

    @FXML
    private TextField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signupHyperlink;

    public void loginButtonClick(ActionEvent event) {
        // String email = loginEmail.getText();
        // String password = loginPassword.getText();

        // if (UserController.isValidEmail(email) == false || UserController.isValidPassword(password) == false) {

        // }


    }

    public void signup(ActionEvent event) {
        // System.out.println("signup");
    }
    
}
