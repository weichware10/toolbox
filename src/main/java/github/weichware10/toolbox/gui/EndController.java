package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Takes care over the functionality and Design of the EndWindow.
 */
public class EndController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private End end;

    public void setEnd(End end) {
        this.end = end;
    }

    @FXML
    void initialize() {

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

}
