package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Controller for {@link ZoomMaps}.
 */
public class ZoomMapsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private ZoomMaps zoomMaps;

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

    }

}
