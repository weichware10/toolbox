package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Takes care over the functionality and Design of the EndWindow.
 */
public class EndController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button jsonButton;

    private End end;

    public void setEnd(End end) {
        this.end = end;
    }

    @FXML
    void closeProgram() {
        Logger.info("end:content Closing Program");
        end.closeProgramm();
    }

    @FXML
    void newTest() {
        Logger.info("end:content Returning to Start");
        end.newTest();
    }

    @FXML
    void saveToJson() {
        Logger.info("end:content Saving Test to JSON");
        end.saveTestToJson();
    }

    @FXML
    void initialize() {
        assert jsonButton != null
                : "fx:id=\"jsonButton\" not injected: check 'End.fxml'.";

    }

}
