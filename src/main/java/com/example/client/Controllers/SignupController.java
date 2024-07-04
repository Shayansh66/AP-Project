package main.java.com.example.client.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignupController {

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
    private Button signupButton;

    @FXML
    private Hyperlink loginHyperlink;

    
    public void signupButtonClick(ActionEvent event) {
        
    }

    public void login(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Login.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
