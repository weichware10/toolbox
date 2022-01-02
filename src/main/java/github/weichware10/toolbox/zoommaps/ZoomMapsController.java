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

    private ZoomMaps zoomMaps;

    public ImageView getImageView() {
        return imageView;
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

    @FXML
    void endTest() {
        Logger.info("zoommaps:content Ending Test");
        zoomMaps.endTest();
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
    }

}
