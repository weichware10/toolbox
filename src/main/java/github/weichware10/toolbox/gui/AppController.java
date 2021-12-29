package github.weichware10.toolbox.gui;

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

    private App app;

    @FXML
    private TextField trialIdField;
    @FXML
    private Text warnText;

    protected void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void openDocumentation() {
        Logger.info("app:menu Opening Docs");
        app.openDocs();
    }

    @FXML
    public void loadJsonConfig() {
        Logger.info("app:menu Loading JSON Config");
        app.loadJsonConfig(warnText);
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
        app.startTrial(trialIdField.getText(), warnText);
    }

    @FXML
    void changeDb() {
        Logger.info("app:menu Starting database conection change");
        app.changeDb();
    }

    @FXML
    void resetDb() {
        Logger.info("app:menu Trying to reset database");
        app.resetDataBaseConnection();
    }

    @FXML
    void createZoomMapsTestTrial() {
        Logger.info("app:menu Creating ZoomMaps Test Trial");
        app.createZoomMapsTestTrial(trialIdField, warnText);
    }

    /**
     * wird durch Menü aufgerufen.
     */
    @FXML
    void createCodeChartsTestTrial() {
        Logger.info("app:menu Creating CodeCharts Test Trial");
        app.createCodeChartsTestTrial(trialIdField, warnText);
    }

    @FXML
    void initialize() {
        assert trialIdField != null
                : "fx:id=\"trialIdField\" not injected: check 'App.fxml'.";
        assert warnText != null
                : "fx:id=\"warnText\" not injected: check 'App.fxml'.";
    }

}
