package main.java.com.example.client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScrollableController {
    
    @FXML
    private Label title;

    @FXML
    private Button object1;

    @FXML
    private Button object2;

    @FXML
    private Button object3;

    @FXML
    private Button object4;

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setObject1() {
        
    }
    
}
