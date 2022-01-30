package github.weichware10.toolbox.codecharts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller für {@link CodeChartsInput}.
 */
public class CodeChartsInputController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    protected TextField inputField;
    @FXML
    protected Text suggestionText;
    @FXML
    protected Text bottomText;
    @FXML
    protected ProgressBar progressBar;

    @FXML
    void initialize() {
        assert inputField != null
                : "fx:id=\"inputField\" not injected: check 'CodeChartsInput.fxml'.";
        assert suggestionText != null
                : "fx:id=\"suggestionText\" not injected: check 'CodeChartsInput.fxml'.";
        assert bottomText != null
                : "fx:id=\"bottomText\" not injected: check 'CodeChartsInput.fxml'.";
        assert progressBar != null
                : "fx:id=\"progressBar\" not injected: check 'CodeChartsInput.fxml'.";
    }

}
