package main.java.com.example.client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;


public class SignupController {
    
    @FXML
    private Button signupButton;

    @FXML
    private TextField signupEmail;

    @FXML
    private TextField signupFirstName;
    
    @FXML
    private TextField signupLastName;
    
    @FXML
    private TextField signupPassword;

    @FXML
    private TextField signupRepeatPassword;

    @FXML
    private Hyperlink loginHyperlink;

    public void signupButtonClick(ActionEvent event) {
        
    }
    
}
