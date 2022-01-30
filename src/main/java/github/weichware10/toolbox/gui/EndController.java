package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller für {@link End}.
 */
public class EndController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private HBox statusBox;
    @FXML
    private Text statusTextLeft;
    @FXML
    private Text statusTextRight;
    @FXML
    private Hyperlink fileLink;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Menu adminMenu;
    @FXML
    private Label outroLabel;

    private End end;
    private String filename = null;
    private ProgressIndicator pi;
    private Stage primaryStage;

    public void setEnd(End end, Stage primaryStage) {
        this.end = end;
        this.primaryStage = primaryStage;
    }

    protected void setAdminMenuVisibile(boolean value) {
        adminMenu.setVisible(value);
    }

    public void setOutro(String outro) {
        outroLabel.setText(outro);
    }

    /**
     * Setzt Status.
     *
     * @param statusLeft -  Nachricht
     * @param filename -  Dateiname
     * @param statusRight -  Nachricht
     */
    public void setStatus(String statusLeft, String filename, String statusRight) {
        setStatusIndicator(false);
        if (statusLeft == null) {
            statusTextLeft.setText("");
            statusTextLeft.setVisible(false);
        } else {
            statusTextLeft.setText(statusLeft);
            statusTextLeft.setVisible(true);
        }
        this.filename = filename;
        if (filename == null) {
            fileLink.setText("");
            fileLink.setVisible(false);
        } else {
            fileLink.setText(filename);
            fileLink.setVisible(true);
        }
        if (statusRight == null) {
            statusTextRight.setText("");
            statusTextRight.setVisible(false);
        } else {
            statusTextRight.setText(statusRight);
            statusTextRight.setVisible(true);
        }
    }

    /**
     * stellt den Status-Indikator an / aus.
     *
     * @param active - ob der Indikator aktiv sein soll oder nicht
     */
    public void setStatusIndicator(boolean active) {
        if (active) {
            setStatus(null, null, null);
            statusBox.getChildren().add(pi);
            buttonBar.setDisable(true);
            primaryStage.setOnCloseRequest(e -> e.consume());
        } else {
            statusBox.getChildren().remove(pi);
            buttonBar.setDisable(false);
            primaryStage.setOnCloseRequest(e -> Platform.exit());
        }
    }

    @FXML
    void closeProgram() {
        Logger.info("end:content Closing Program");
        Platform.exit();
    }

    @FXML
    void newTest() {
        Logger.info("end:content Returning to Start");
        end.newTest();
    }

    @FXML
    void saveToJson() {
        Logger.info("end:content Saving Test to JSON");
        end.saveTestToJson();
    }

    @FXML
    void saveToDataBase() {
        Logger.info("end:content Saving Test to DataBase");
        end.saveTestToDataBase();
    }

    @FXML
    void openFile() {
        if (filename == null) {
            return;
        }
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                Desktop.getDesktop().open(new File(filename));
            } catch (IOException e) {
                Logger.error("error occured while opening documentation", e);
            }
        }
    }

    @FXML
    void initialize() {
        assert statusTextLeft != null
                : "fx:id=\"statusTextLeft\" not injected: check 'End.fxml'.";
        assert statusTextRight != null
                : "fx:id=\"statusTextRight\" not injected: check 'End.fxml'.";
        assert fileLink != null
                : "fx:id=\"fileLink\" not injected: check 'End.fxml'.";
        assert statusBox != null
                : "fx:id=\"statusBox\" not injected: check 'End.fxml'.";
        assert buttonBar != null
                : "fx:id=\"buttonBar\" not injected: check 'End.fxml'.";
        assert adminMenu != null
                : "fx:id=\"adminMenu\" not injected: check 'End.fxml'.";
        assert outroLabel != null
                : "fx:id=\"outroLabel\" not injected: check 'End.fxml'.";

        pi = new ProgressIndicator();
        pi.setPrefHeight(statusBox.getHeight());
    }

}
