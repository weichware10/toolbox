package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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
