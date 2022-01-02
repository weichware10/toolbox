package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.gui.util.Log;
import github.weichware10.util.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
    private Menu adminMenu;
    @FXML
    private Button startButton;
    @FXML
    private TextField trialIdField;
    @FXML
    private Text warnText;
    @FXML
    private MenuItem logsMenu;

    protected void setApp(App app) {
        this.app = app;
    }

    protected void setAdminMenuVisibile(boolean value) {
        adminMenu.setVisible(value);
    }

    protected void setWarnText(String value) {
        if (value == null) {
            warnText.setVisible(false);
            return;
        }
        warnText.setText(value);
        warnText.setVisible(true);
    }

    protected void setTrialInputDisable(boolean value) {
        setWarnText(value ? "starting tests is disabled - check database user" : null);
        startButton.setDisable(value);
        trialIdField.setDisable(value);
    }

    @FXML
    void openDocumentation() {
        Logger.info("app:menu Opening Docs");
        app.openDocs();
    }

    @FXML
    void loadJsonConfig() {
        Logger.info("app:menu Loading JSON Config");
        app.loadJsonConfig();
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
        app.startTrial(trialIdField.getText());
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
        app.createZoomMapsTestTrial(trialIdField);
    }

    @FXML
    void createCodeChartsTestTrial() {
        Logger.info("app:menu Creating CodeCharts Test Trial");
        app.createCodeChartsTestTrial(trialIdField);
    }

    @FXML
    void toggleLogs() {
        Logger.info("app:menu Toggling logs");
        if (Log.isVisible()) {
            Log.hide();
            logsMenu.setText("show logs");
        } else {
            Log.show();
            logsMenu.setText("hide logs");
        }
    }

    @FXML
    void initialize() {
        assert trialIdField != null
                : "fx:id=\"trialIdField\" not injected: check 'App.fxml'.";
        assert warnText != null
                : "fx:id=\"warnText\" not injected: check 'App.fxml'.";
        assert adminMenu != null
                : "fx:id=\"adminMenu\" not injected: check 'App.fxml'.";
        assert startButton != null
                : "fx:id=\"startButton\" not injected: check 'App.fxml'.";
        assert logsMenu != null
                : "fx:id=\"logsMenu\" not injected: check 'App.fxml'.";
    }

}
