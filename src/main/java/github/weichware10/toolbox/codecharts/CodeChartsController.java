package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Controller for {@link CodeCharts}.
 */
public class CodeChartsController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private BorderPane codeChartsBorderPane;
    @FXML
    private ImageView codeChartsImageView;
    @FXML
    private StackPane codeChartsStackPane;
    @FXML
    private GridPane codeChartsGridPane;

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
        codeChartsGridPane.setPrefWidth(width.get());
    }

    private void setImageViewHeight(ObservableDoubleValue height) {
        codeChartsImageView.setFitHeight(height.get());
        codeChartsGridPane.setPrefHeight(height.get());
    }

    private void setGridPane(){
        codeChartsGridPane.add(new TextField("Test"), 0, 0);
        codeChartsGridPane.add(new TextField("Test"), 3, 3);
    }

    @FXML
    void initialize() {
        assert codeChartsBorderPane != null :
        "fx:id=\"codeChartsBorderPane\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        assert codeChartsGridPane != null :
        "fx:id=\"codeChartsGridPane\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        assert codeChartsImageView != null :
        "fx:id=\"codeChartsImageView\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        assert codeChartsStackPane != null :
        "fx:id=\"codeChartsStackPane\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        codeChartsBorderPane.widthProperty().addListener(
                obs -> setImageViewWidth((ObservableDoubleValue) obs));
        codeChartsBorderPane.heightProperty().addListener(
                obs -> setImageViewHeight((ObservableDoubleValue) obs));
        setGridPane();
    }
}
