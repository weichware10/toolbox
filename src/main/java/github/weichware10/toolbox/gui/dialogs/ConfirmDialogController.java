package github.weichware10.toolbox.gui.dialogs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Managed ConfirmDialoge.
 */
public class ConfirmDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label promptLabel;

    @FXML
    private DialogPane dialogPane;

    protected void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }

    protected void setImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        dialogPane.setGraphic(imageView);
    }

    @FXML
    void initialize() {
        assert promptLabel != null
                : "fx:id=\"promptLabel\" not injected: check 'ConfirmDialog.fxml'.";
        assert dialogPane != null
                : "fx:id=\"dialogPane\" not injected: check 'ConfirmDialog.fxml'.";

    }

}
