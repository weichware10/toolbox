package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

/**
 * Takes care over the functionality and Design of the EndWindow.
 */
public class EndController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text statusTextLeft;
    @FXML
    private Text statusTextRight;
    @FXML
    private Hyperlink fileLink;

    private End end;
    private String filename = null;

    public void setEnd(End end) {
        this.end = end;
    }

    /**
     * Setzt Status.
     *
     * @param statusLeft -  Nachricht
     * @param filename -  Dateiname
     * @param statusRight -  Nachricht
     */
    public void setStatus(String statusLeft, String filename, String statusRight) {
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
     * Setzt File Link.
     *
     * @param filename - Pfad der Datei.
     */
    public void setFileLink(String filename) {
        this.filename = filename;
        if (filename == null) {
            fileLink.setVisible(false);
        } else {
            fileLink.setText(filename);
            fileLink.setVisible(true);
        }
    }

    @FXML
    void closeProgram() {
        Logger.info("end:content Closing Program");
        end.closeProgramm();
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

    }

}
