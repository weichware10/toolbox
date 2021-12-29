package github.weichware10.toolbox.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Takes care of the functionality of PreTestWindow.
 */
public class PreTestWindow {
    /**
     * This function shows the PreTestWindow.
     *
     * @param primaryStage - primary Window to do changes on.
     */
    public static void display(Stage primaryStage) {
        primaryStage.setTitle("Test starting");

        FXMLLoader loader = new FXMLLoader(
            PreTestWindowController.class.getResource("PreTestWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }
}
