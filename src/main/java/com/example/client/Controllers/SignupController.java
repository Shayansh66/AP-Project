package main.java.com.example.client.Controllers;

import main.java.com.example.server.controllers.UserController;
import main.java.com.example.server.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ConnectException;
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

import org.json.JSONArray;
import org.json.JSONObject;

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

    public void signupButtonClick(ActionEvent event) {
        String email = signupEmail.getText();
        String firstName = signupFirstName.getText();
        String lastName = signupLastName.getText();
        String password = signupPassword.getText();
        String repeatPassword = signupRepeatPassword.getText();

        // Validate input fields
        if (!UserController.isValidEmail(email)) {
            wrongInputLabel.setText("Please enter a correct email");
            return;
        } else if (firstName.isEmpty() || lastName.isEmpty()) {
            wrongInputLabel.setText("Please fill all sections");
            return;
        } else if (!UserController.isValidPassword(password)) {
            wrongInputLabel.setText("Password must include at least 8 characters, a letter, and a number");
            return;
        } else if (!password.equals(repeatPassword)) {
            wrongInputLabel.setText("Password and its repeat are not the same");
            return;
        }

        try {
            // Check if email already exists
            if (emailExists(email)) {
                wrongInputLabel.setText("Email is already in use");
                return;
            }

            // Proceed with user creation
            User user = new User(0, email, password, firstName, lastName, null, null, "", null, null);

            // Convert user object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(user);

            // Send POST request to server
            URL url = new URL("http://localhost:8080/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON data to output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Handle response from server
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                String responseBody = response.toString();
                if (responseBody.equals("successful")) {
                    // Successful signup
                    System.out.println("Signup successful");
                } else {
                    // Failed signup
                    System.err.println("Signup failed");
                }
            }

            connection.disconnect();

        } catch (ConnectException e) {
            wrongInputLabel.setText("Connection failed");
            e.printStackTrace();
        } catch (IOException e) {
            wrongInputLabel.setText("Failed to communicate with the server");
            e.printStackTrace();
        }
    }

    private boolean emailExists(String email) throws IOException {
        // Send GET request to check if email already exists
        URL url = new URL("http://localhost:8080/users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Check if email exists in the response
                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("email").equals(email)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
