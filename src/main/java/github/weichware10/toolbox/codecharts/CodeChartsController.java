package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Controller for {@link CodeCharts}.
 */
public class CodeChartsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    }

}
