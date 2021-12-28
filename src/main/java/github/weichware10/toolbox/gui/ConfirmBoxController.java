package github.weichware10.toolbox.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Funktionen für die grafischen Elemente.
 */
public class ConfirmBoxController {

    private Stage primaryStage;
    private Stage window;

    public void setStage(Stage primaryStage, Stage window) {
        this.primaryStage = primaryStage;
        this.window = window;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    @FXML
    void closeProgramm() {
        window.close();
        primaryStage.close();
    }

    @FXML
    void closeWindow() {
        window.close();
    }

}
