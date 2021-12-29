package github.weichware10.toolbox.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 * Takes care over the functionality and Design of the EndWindow.
 */
public class EndWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    @FXML
    void close() {
        EndWindow.closeProgramm();
    }

    @FXML
    //TODO: Zum Start kommen
    void newTest() {

    }

}
