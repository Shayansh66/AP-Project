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

    
    public void signupButtonClick(ActionEvent event) throws IOException {
        String email = signupEmail.getText();
        String firstName = signupFirstName.getText();
        String lastName = signupLastName.getText();
        String password = signupPassword.getText();
        String repeatPassword = signupRepeatPassword.getText();

        
        if (UserController.isValidEmail(email) == false) {
            wrongInputLabel.setText("please enter correct email");
            return;
        }
        else if (firstName.length() == 0 || lastName.length() == 0) {
            wrongInputLabel.setText("please fill all sections");
            return;
        }
        else if (UserController.isValidPassword(password) == false) {
            wrongInputLabel.setText("password must include at least 8 character\nand a letter andd number");
            return;
        }
        else if (password.equals(repeatPassword) == false) {
            wrongInputLabel.setText("password and its repeat are not same!");
            return;
        }
        else {

            try {
                String response = new String();
                {
                URL url = new  URL("http://localhost:8080/users");
                HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responsecode = connection.getResponseCode();
                BufferedReader in = new  BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputline;
                StringBuffer response1 = new StringBuffer();
                while ((inputline = in.readLine()) != null) {
                    response1.append(inputline);
                }
                in.close();
                 response = response1.toString();
                }

                JSONArray jsonObject = new JSONArray();
                String[] users = toStringArray(jsonObject);
                boolean Email_existed = false;
                for (String t: users) {
                    JSONObject obj = new JSONObject(t);
                    User user = new User();
                    user.setEmail(email);
                    if (user.getEmail().equals(email) && email.length() != 0)
                        Email_existed = true;
            }
            if (Email_existed) {
                wrongInputLabel.setText("email is already used");
            }
            else {
                User user = new User();
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastname(lastName);
                user.setPassword(repeatPassword);
                URL url = new URL("http://localhost:8080/users");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(user);

                        byte[] postDataBytes = json.getBytes();
                        
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.getOutputStream().write(postDataBytes);

                        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder sb = new StringBuilder();

                        for (int c; (c = in.read()) > 0; )
                        sb.append((char) c);
                        response = sb.toString();

                        if (response.equals("sucsessful")) {
                            // sucsessful signup
                        }
                        else {
                        // failed signip
                        }

            }
        }
            catch (ConnectException e) {
                wrongInputLabel.setText("connection failed");
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
    public static String[] toStringArray(JSONArray array) {
        if(array == null)
            return new String[0];

        String[] arr = new String[array.length()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = array.optString(i);
        return arr;
    }
}


