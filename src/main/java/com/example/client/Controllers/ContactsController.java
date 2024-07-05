package main.java.com.example.client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.example.server.models.Contact;

public class ContactsController {

    @FXML
    private TextField emailTextField;
    
    @FXML
    private TextField phoneTextField;
    
    @FXML
    private TextField addressTextField;
    
    
    public void load(Parent root, Contact contact) {
        
            Scene scene = new Scene(root);
            
            emailTextField.setText(contact.getEmail());
            phoneTextField.setText(contact.getPhonenumber());
            addressTextField.setText(contact.getAddress());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
    }
    
}
