package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
        double ratio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        double width = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * ratio);
        double height = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / ratio);
        CodeChartsPane ccPane = new CodeChartsPane(new ArrayList<>(), -1, -1, width, height);
        stackPane.getChildren().add(ccPane);
        ccPane.subdivide(3, 3);
    }
}
