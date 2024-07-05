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
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;

// Other imports...

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

    private ObjectMapper objectMapper = new ObjectMapper();
    public void loginButtonClick(ActionEvent event) {
        System.out.println("Login button clicked");
    
        String email = loginEmail.getText();
        String password = loginPassword.getText();
    
        if (!UserController.isValidEmail(email) || !UserController.isValidPassword(password)) {
            wrongInputLabel.setText("Please enter correct format!");
            System.out.println("Invalid email or password format");
        } else {
            try {
                URL url = new URL("http://localhost:8080/sessions/" + email + "/" + password);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
    
                // If your API requires additional headers, add them here
                // con.setRequestProperty("Authorization", "Bearer YOUR_TOKEN");
    
                int responseCode = con.getResponseCode();
                System.out.println("Response Code: " + responseCode);
    
                if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    wrongInputLabel.setText("Email or Password is incorrect");
                    System.out.println("401 Unauthorized - Email or Password is incorrect");
                    return;
                }
    
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response1 = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response1.append(inputLine);
                }
                in.close();
                String response = response1.toString();
                System.out.println("Response: " + response);
    
                if (response.equals("the email and password is incorrect")) {
                    wrongInputLabel.setText("Email or Password is incorrect");
                } else {
                    User loggedInUser = objectMapper.readValue(response, User.class);
                    Session.getInstance().setLoggedInUser(loggedInUser);
    
                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Profile.fxml"));
                        Scene scene = new Scene(root);
    
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ConnectException e) {
                wrongInputLabel.setText("Could not connect to the server. Please try again later.");
                System.out.println("Connection Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                wrongInputLabel.setText("An unexpected error occurred.");
            }
        }
    }
    

    public void signup(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Signup.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            wrongInputLabel.setText("Failed to load signup page.");
        }
    }
}
