package github.weichware10.toolbox.gui.dialogs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

/**
 * Controller für {@link LogDialog}.
 */
public class LogDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane logScrollPane;

    @FXML
    private Text logText;

    public void setLog(String log) {
        logText.setText(log);
    }

    @FXML
    void initialize() {
        assert logScrollPane != null
                : "fx:id=\"logScrollPane\" not injected: check 'LogDialog.fxml'.";
        assert logText != null
                : "fx:id=\"logText\" not injected: check 'LogDialog.fxml'.";

        // bind width
        logText.wrappingWidthProperty().bind(logScrollPane.widthProperty());

    }

}
