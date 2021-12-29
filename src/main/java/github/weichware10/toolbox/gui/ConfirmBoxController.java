package github.weichware10.toolbox.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Funktionen für die grafischen Elemente.
 */
public class ConfirmBoxController {

    private Stage primaryStage;
    private Stage window;

    public void setStage(Stage primaryStage, Stage window) {
        this.primaryStage = primaryStage;
        this.window = window;
    }

    /**
     * This function shows the window with a yes or no decission.
     *
     * @param primaryStage - primary Window to do changes on.
     */
    public static void display(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(
            ConfirmBoxController.class.getResource("ConfirmBox.fxml"));
        //TODO: Joshua Fragen was hier Sache ist
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Stage window = new Stage();

        ConfirmBoxController controller = loader.getController();
        controller.setStage(primaryStage, window);
        Scene scene = new Scene(root, 300, 150);

        window.setScene(scene);
        window.show();
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
        window.close();
        primaryStage.close();
    }

    @FXML
    void closeWindow() {
        window.close();
    }

}
