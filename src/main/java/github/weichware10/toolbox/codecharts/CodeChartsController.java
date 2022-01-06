package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableDoubleValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
        //codeChartsGridPane.setPrefWidth(width.get());
    }

    private void setImageViewHeight(ObservableDoubleValue height) {
        codeChartsImageView.setFitHeight(height.get());
        //codeChartsGridPane.setPrefHeight(height.get());
    }

    /**
     * Stellt das Grid ein.
     *
     * @param rows - Anzahl der Zeilen
     * @param collums - Anzahl der Spalten
     */
    //TODO: Rows und Collums in COnfig anpassen 10x10 viel zu groß
    public void setGridPane(int rows, int collums) {
        int counter = 0;
        ArrayList<String> elemente = CodeChartsListe.getElemente();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                codeChartsGridPane.add(new Label(elemente.get(counter)), j, i);
                counter++;
            }
        }
    }

    @FXML
    void initialize() {
        assert codeChartsGridPane != null :
        "fx:id=\"codeChartsGridPane\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        assert codeChartsImageView != null :
        "fx:id=\"codeChartsImageView\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        assert codeChartsStackPane != null :
        "fx:id=\"codeChartsStackPane\" was not injected: check your FXML file 'CodeCharts.fxml'.";
        codeChartsStackPane.widthProperty().addListener(
                obs -> setImageViewWidth((ObservableDoubleValue) obs));
        codeChartsStackPane.heightProperty().addListener(
                obs -> setImageViewHeight((ObservableDoubleValue) obs));
    }
}
