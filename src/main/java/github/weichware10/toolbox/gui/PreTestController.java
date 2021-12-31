package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller für {@link PretestWindow}.
 */
public class PreTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label promptLabel;

    private PreTest preTest;

    public void setPreTest(PreTest preTest) {
        this.preTest = preTest;
    }

    @FXML
    void startTest(ActionEvent event) {
        Logger.info("pretest:content Starting test");
        preTest.startTest();
    }

    void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }


    @FXML
    void initialize() {
        assert promptLabel != null
                : "fx:id=\"promptLabel\" not injected: check 'PreTestWindow.fxml'.";

    }

}
