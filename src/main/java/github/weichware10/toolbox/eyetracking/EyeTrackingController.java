package github.weichware10.toolbox.eyetracking;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Controller für {@link EyeTracking}.
 */
public class EyeTrackingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private EyeTracking eyeTracking;

    public void setEyeTracking(EyeTracking eyeTracking) {
        this.eyeTracking = eyeTracking;
    }

    @FXML
    void endTest() {
        Logger.info("eyetracking:content Ending Test");
        eyeTracking.endTest();
    }

    @FXML
    void initialize() {

    }

}
