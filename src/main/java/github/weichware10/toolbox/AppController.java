package github.weichware10.toolbox;

import github.weichware10.util.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * Controller für Einstieg in App.
 */
public class AppController {

    private Main main;

    @FXML
    private TextField trialIdField;
    @FXML
    private Text warnText;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void openDocumentation() {
        Logger.info("app:menu Opening Docs");
    }

    @FXML
    public void loadJsonConfig() {
        Logger.info("app:menu Loading JSON Config");
        main.loadJsonConfig(warnText);
    }

    @FXML
    void trialIdEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            startTrial();
        }
    }

    @FXML
    void startTrial() {
        Logger.info("app:content Trying to start Trial");
        main.startTrial(trialIdField.getText(), warnText);
    }

    @FXML
    void initialize() {
        assert trialIdField != null
                : "fx:id=\"trialIdField\" was not injected: check your FXML file 'App.fxml'.";
        assert warnText != null
                : "fx:id=\"warnText\" was not injected: check your FXML file 'App.fxml'.";
    }
}
