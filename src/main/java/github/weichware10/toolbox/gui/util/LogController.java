package github.weichware10.toolbox.gui.util;

import github.weichware10.toolbox.Console;
import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Controller für {@link Log}.
 */
public class LogController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;


    @FXML
    private TextField logInput;
    @FXML
    private TextArea logArea;
    @FXML
    private Pane logPane;

    @FXML
    void initialize() {
        assert logArea != null
                : "fx:id=\"logArea\" not injected: check 'Log.fxml'.";
        assert logPane != null
                : "fx:id=\"logPane\" not injected: check 'Log.fxml'.";
        assert logInput != null
                : "fx:id=\"logInput\" not injected: check 'Log.fxml'.";
        logArea.minHeightProperty().bind(logPane.heightProperty());
        logArea.minWidthProperty().bind(logPane.widthProperty());
        Console.setLogArea(logArea);
    }

    @FXML
    void inputKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            log();
        }
    }

    @FXML
    void log() {
        Logger.debug(logInput.getText());
        logInput.clear();
    }
}
