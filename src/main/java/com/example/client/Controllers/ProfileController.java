package main.java.com.example.client.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import main.java.com.example.server.models.Contact;
import main.java.com.example.server.models.User;

public class ProfileController {

    @FXML
    private Hyperlink contactsHyperlink;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void showContacts(ActionEvent event) {
        try {
            // Fetch logged-in user
            User loggedInUser = Session.getInstance().getLoggedInUser();
            int userId = loggedInUser.getId();

            // Make GET request to fetch contacts
            String response = getContactsByUserId(userId);

            // Deserialize response into Contact objects
            List<Contact> contacts = Arrays.asList(objectMapper.readValue(response, Contact[].class));

            // Here you can handle the contacts list to display in the UI


            // Load the Contacts.fxml scene
            Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Contacts.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContactsByUserId(int userId) throws IOException {
        URL url = new URL("http://localhost:8080/contacts/" + userId);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == 200) { // OK
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("Failed to fetch contacts, response code: " + responseCode);
        }
    }
}
