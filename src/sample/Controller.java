package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Finder;

    @FXML
    private TextField Input_money;

    @FXML
    void initialize() {
        Finder.setOnAction(event -> {
            System.out.println("Работает!");
        });
    }
}
