package github.weichware10.toolbox;

import java.io.IOException;

import github.weichware10.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
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
    void changeDb() {
        Logger.info("app:menu Starting database conection change");
        main.changeDb();
    }

    @FXML
    void resetDb() {
        Logger.info("app:menu Trying to reset database");
        main.resetDataBaseConnection();
    }

    @FXML
    void createZoomMapsTestTrial() {
        Logger.info("app:menu Creating ZoomMaps Test Trial");
        main.createZoomMapsTestTrial(trialIdField);
    }

    @FXML
    void createCodeChartsTestTrial() {
        Logger.info("app:menu Creating CodeCharts Test Trial");
        main.createCodeChartsTestTrial(trialIdField);
    }

    @FXML
    void initialize() {
        assert trialIdField != null
                : "fx:id=\"trialIdField\" was not injected: check your FXML file 'App.fxml'.";
        assert warnText != null
                : "fx:id=\"warnText\" was not injected: check your FXML file 'App.fxml'.";
    }

}
