package main.java.com.example.client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Linkedin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/main/Resource/com/example/client/Test.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Image icon = new Image("/main/Resource/images/icon.jpg");
            
            stage.getIcons().add(icon);
            stage.setTitle("Linkedin");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
