package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Controller for {@link CodeCharts}.
 */
public class CodeChartsController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView codeChartsImageView;
    @FXML
    private Pane codeChartsPane;

    private CodeCharts codeCharts;

    public void setCodeCharts(CodeCharts codeCharts) {
        this.codeCharts = codeCharts;
    }

    @FXML
    void endTest() {
        Logger.info("codecharts:content Ending Test");
        codeCharts.endTest();
    }

    private void setImageViewWidth(ObservableDoubleValue width) {
        codeChartsImageView.setFitWidth(width.get());
    }

    private void setImageViewHeight(ObservableDoubleValue height) {
        codeChartsImageView.setFitHeight(height.get());
    }

    @FXML
    void initialize() {
        assert codeChartsImageView != null
                : "fx:id=\"codeChartsImageView\" not injected: check 'CodeCharts.fxml'.";
        assert codeChartsPane != null
                : "fx:id=\"codeChartsPane\" not injected: check 'CodeCharts.fxml'.";
        codeChartsPane.widthProperty().addListener(
                obs -> setImageViewWidth((ObservableDoubleValue) obs));
        codeChartsPane.heightProperty().addListener(
                obs -> setImageViewHeight((ObservableDoubleValue) obs));
    }
}
