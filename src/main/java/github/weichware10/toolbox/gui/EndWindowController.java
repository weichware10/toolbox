package github.weichware10.toolbox.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import github.weichware10.toolbox.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Takes care over the functionality and Design of the EndWindow.
 */
public class EndWindowController {
    private Stage primaryStage;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * This function shows the window with a yes or no decission.
     *
     * @param primaryStage - primary Window to do changes on.
     */
    public static void display(Stage primaryStage) {
        primaryStage.setTitle("Test finished");

        FXMLLoader loader = new FXMLLoader(
            EndWindowController.class.getResource("EndWindow.fxml"));
        //TODO: Joshua Fragen was hier Sache ist
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        EndWindowController controller = loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    @FXML
    void closeProgramm() {
        primaryStage.close();
    }

    @FXML
    //TODO: Zum Start kommen
    void newTest() {

    }

}
