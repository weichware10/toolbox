package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * Kontrollierer für {@link ZoomMaps}.
 */
public class ZoomMapsController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField answerField;
    @FXML
    private ImageView imageView;
    @FXML
    private Label questionLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Text warnText;

    private ZoomMaps zoomMaps;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageViewSize(double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void setQuestion(String question) {
        this.questionLabel.setText(question);
    }

    public void setZoomMaps(ZoomMaps zoomMaps) {
        this.zoomMaps = zoomMaps;
    }

    /**
     * Setzt Warnung.
     *
     * @param warn -  Nachricht
     */
    public void setWarn(String warn) {
        if (warn == null) {
            warnText.setVisible(false);
        } else {
            warnText.setText(warn);
            warnText.setVisible(true);
        }
    }

    @FXML
    void endTestOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            endTest();
        }
    }

    @FXML
    void endTest() {
        Logger.info("zoommaps:content Ending Test");
        zoomMaps.endTest(answerField.getText());
    }

    @FXML
    void initialize() {
        assert answerField != null
                : "fx:id=\"answerField\" not injected: check 'ZoomMaps.fxml'.";
        assert imageView != null
                : "fx:id=\"imageView\" not injected: check 'ZoomMaps.fxml'.";
        assert questionLabel != null
                : "fx:id=\"questionLabel\" not injected: check 'ZoomMaps.fxml'.";
        assert submitButton != null
                : "fx:id=\"submitButton\" not injected: check 'ZoomMaps.fxml'.";
        assert warnText != null
            : "fx:id=\"warnText\" not injected: check 'ZoomMaps.fxml'.";

    }

}
