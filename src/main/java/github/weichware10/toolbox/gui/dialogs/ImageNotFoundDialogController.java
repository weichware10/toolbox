package github.weichware10.toolbox.gui.dialogs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Kontroller für {@link ImageNotFoundDialog}.
 */
public class ImageNotFoundDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea errorMessage;

    protected void setErrorMessage(String error) {
        errorMessage.setText(error);
    }

    @FXML
    void initialize() {
        assert errorMessage != null
                : "fx:id=\"errorMessage\" not injected: check 'ImageNotFoundDialog.fxml'.";

    }

}
