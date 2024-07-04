package main.java.com.example.client.Controllers;

import main.java.com.example.server.controllers.UserController;
import main.java.com.example.server.models.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    @FXML
    private Label wrongInputLabel;

    
    public void signupButtonClick(ActionEvent event) throws IOException {
        String email = signupEmail.getText();
        String firstName = signupFirstName.getText();
        String lastName = signupLastName.getText();
        String password = signupPassword.getText();
        String repeatPassword = signupRepeatPassword.getText();

        
        if (UserController.isValidEmail(email) == false) {
            wrongInputLabel.setText("");
        }
        else if (password.equals(repeatPassword) == false) {
            wrongInputLabel.setText("password and its repeat are not same!");
        }
        else {
            User user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastname(lastName);
            user.setPassword(repeatPassword);


            ObjectMapper objectMapper = new ObjectMapper();
            String bodyRequest;
            bodyRequest = objectMapper.writeValueAsString(user);

            URL url;
            url = new URL("http://localhost:8080/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(bodyRequest.getBytes());
            outputStream.flush();
            outputStream.close();
            int responseCode = connection.getResponseCode(); 
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
            }
            else {
                
            }


        }



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
