package github.weichware10.toolbox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller für DataBaseDialog.
 * startet keine Funktionen, ist nur als Zugriff auf Textfelder usw. zuständig.
 */
public class DataBaseDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text errorText;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField schemaTextField;

    @FXML
    private TextField urlTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Text warningText;


    protected String getPassword() {
        return passwordField.getText();
    }

    protected String getSchema() {
        return schemaTextField.getText();
    }

    protected String getUrl() {
        return urlTextField.getText();
    }

    protected String getUsername() {
        return usernameTextField.getText();
    }

    protected void setError(String error) {
        this.errorText.setText(error);
        this.errorText.setVisible(true);
    }

    protected void setWarning(String warning) {
        this.warningText.setText(warning);
        this.warningText.setVisible(true);
    }

    @FXML
    void initialize() {
        assert errorText != null
                : "fx:id=\"errorText\" not injected: check 'DataBaseDialog.fxml'.";
        assert passwordField != null
                : "fx:id=\"passwordField\" not injected: check 'DataBaseDialog.fxml'.";
        assert schemaTextField != null
                : "fx:id=\"schemaTextField\" not injected: check 'DataBaseDialog.fxml'.";
        assert urlTextField != null
                : "fx:id=\"urlTextField\" not injected: check 'DataBaseDialog.fxml'.";
        assert usernameTextField != null
                : "fx:id=\"usernameTextField\" not injected: check 'DataBaseDialog.fxml'.";
        assert warningText != null
                : "fx:id=\"warningText\" not injected: check 'DataBaseDialog.fxml'.";

    }

}
