package github.weichware10.toolbox.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Managed ConfirmBoxen.
 */
public class ConfirmBoxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label promptLabel;

    protected void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }

    @FXML
    void initialize() {
        assert promptLabel != null
                : "fx:id=\"promptLabel\" was not injected: check your FXML file 'Untitled'.";

    }

}
