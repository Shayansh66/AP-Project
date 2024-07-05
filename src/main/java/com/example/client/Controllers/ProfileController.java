package main.java.com.example.client.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class ProfileController {
    
    @FXML
    private Hyperlink contactsHyperlink;


    public void showContacts(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Contacts.fxml"));
            Scene scene = new Scene(root);

                                                                /*************************/

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
