package github.weichware10.toolbox.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Takes care of the functionality for the EndWindow.
 */
public class EndWindow {
    private static Stage primaryStage;

    /**
     * This function shows the EndWindow.
     *
     * @param primaryStage - primary Window to do changes on.
     */
    public void display(Stage primaryStage) {
        primaryStage.setTitle("Test finished");
        EndWindow.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(
            EndWindowController.class.getResource("EndWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void closeProgramm() {
        primaryStage.close();
    }
}
