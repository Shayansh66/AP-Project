package main.java.com.example.client.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.example.server.models.Contact;

public class ContactsController implements Initializable {

    @FXML
    private TextField emailTextField;
    
    @FXML
    private TextField phoneTextField;
    
    @FXML
    private TextField addressTextField;

    @FXML
    private Label phoneNumberLabel;
    
    @FXML
    private ChoiceBox<Integer> birthdayMonth;

    @FXML
    private ChoiceBox birthdayDay;

    @FXML
    private ChoiceBox birthdayVisibility;
    
    public void load(Parent root, Contact contact) {
         
        Scene scene = new Scene(root);
            
        emailTextField.setText(contact.getEmail());
        phoneTextField.setText(contact.getPhonenumber());
        phoneNumberLabel.setText("Phone(" + contact.getPhoneType() + ")");
        addressTextField.setText(contact.getAddress());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        birthdayMonth.getItems().addAll(list);

    }
    
}
