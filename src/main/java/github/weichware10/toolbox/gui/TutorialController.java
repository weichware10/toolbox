package github.weichware10.toolbox.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TutorialController {
    private Tutorial tutorial;
    private int tutorialCount = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageViewId;

    @FXML
    private HBox hBoxId;

    @FXML
    private Button nextButtonId;

    @FXML
    private Button backButtonId;

    @FXML
    private Text progressTextId;

    @FXML
    private Button finishButtonId;

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public ImageView getImageView() {
        return imageViewId;
    }

    public Text getProgressText() {
        return progressTextId;
    }

    public HBox getHbox() {
        return hBoxId;
    }

    @FXML
    void setNextImage() {
        tutorialCount++;
        Tutorial.setImage(tutorialCount, nextButtonId, backButtonId, imageViewId, progressTextId);
    }

    @FXML
    void setPreviousImage() {
        tutorialCount--;
        Tutorial.setImage(tutorialCount, nextButtonId, backButtonId, imageViewId, progressTextId);
    }

    @FXML
    void endTutorial() {
        tutorial.startTest();
    }

    @FXML
    void initialize() {
        assert imageViewId != null : "fx:id=\"imageViewID\" was not injected: check your FXML file 'Tutorial.fxml'.";
        assert hBoxId != null : "fx:id=\"hBoxId\" was not injected: check your FXML file 'Tutorial.fxml'.";
        assert nextButtonId != null : "fx:id=\"nextButtonId\" was not injected: check your FXML file 'Tutorial.fxml'.";
        assert backButtonId != null : "fx:id=\"backButtonId\" was not injected: check your FXML file 'Tutorial.fxml'.";
        assert progressTextId != null : "fx:id=\"progressTextId\" was not injected: check your FXML file 'Tutorial.fxml'.";
        assert finishButtonId != null : "fx:id=\"finishButtonId\" was not injected: check your FXML file 'Tutorial.fxml'.";
        imageViewId.fitHeightProperty().bind(hBoxId.heightProperty());
        imageViewId.fitWidthProperty().bind(hBoxId.widthProperty());
        backButtonId.setDisable(true);
    }

}
