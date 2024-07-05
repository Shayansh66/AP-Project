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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.example.server.models.Contact;
import main.java.com.example.server.models.User;

public class ProfileController {

    @FXML
    private Label wrongInputLabel;

    @FXML
    private Hyperlink contactsHyperlink;

    @FXML
    private Button applyButton;

    @FXML
    private TextArea headtitle;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField additionalname;

    @FXML
    private TextField country;

    @FXML
    private TextField city;

    @FXML
    private TextField profession;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void showContacts(ActionEvent event) {
        try {
            
            User loggedInUser = Session.getInstance().getLoggedInUser();
            int userId = loggedInUser.getId();

            String response = getContactsByUserId(userId);
            List<Contact> contacts = Arrays.asList(objectMapper.readValue(response, Contact[].class));

            Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Contacts.fxml"));
            ContactsController contact = new ContactsController();
            contact.load(root, contacts.get(0));

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

    public void applyUpdate(ActionEvent event) {
        String headTitle = headtitle.getText();
        String firstName = firstname.getText();
        String lastName = lastname.getText();
        String additionalName = additionalname.getText();
        String countryLocation = country.getText();
        String cityLocation = city.getText();
        String theProfession = profession.getText();
        
        if (headTitle.length() > 220) {
            wrongInputLabel.setText("head title must not be more than 220\ncharacters!");
            return ;
        }
        else if (firstName.length() > 20) {
            wrongInputLabel.setText("first name must not be more than 20\ncharacters");
            return ;
        }
        else if (lastName.length() > 40) {
            wrongInputLabel.setText("last name must not be more than 40 \ncharacters");
            return ;
        }
        else if (additionalName.length() > 40) {
            wrongInputLabel.setText("additional name must not be more\nthan 40characters");
            return ;
        }
        else if (countryLocation.length() > 40) {
            wrongInputLabel.setText("country must not be morethan 60 characters");
            return ;
        }
        else if (cityLocation.length() > 40) {
            wrongInputLabel.setText("city must not be morethan 60 characters");
            return ;
        }
        else if (theProfession.length() > 40) {
            wrongInputLabel.setText("profession must not be morethan 60 characters");
            return ;
        }

        /******************************************************************** correct info */

    }
}
