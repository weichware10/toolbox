package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * Controller fuer {@link CodeCharts}.
 */
public class CodeChartsController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView imageView;
    @FXML
    private StackPane stackPane;

    private CodeCharts codeCharts;

    public void setCodeCharts(CodeCharts codeCharts) {
        this.codeCharts = codeCharts;
    }

    @FXML
    void endTest() {
        Logger.info("codecharts:content Ending Test");
        codeCharts.endTest();
    }

    @FXML
    void initialize() {
        assert imageView != null
                : "fx:id=\"codeChartsImageView\" not injected: check 'CodeCharts.fxml'.";
        assert stackPane != null
                : "fx:id=\"codeChartsStackPane\" not injected: check 'CodeCharts.fxml'.";

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        imageView.setFitWidth(screenBounds.getWidth() / 2);
        imageView.setFitHeight(screenBounds.getHeight() / 2);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
