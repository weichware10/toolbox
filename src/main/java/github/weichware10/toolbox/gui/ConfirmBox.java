package github.weichware10.toolbox.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Kümmert sich um das Design der Rückfrage.
 */
public class ConfirmBox {
    /**
     * Die eigentliche Funktion, die den Startbildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     */
    public static void display(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(ConfirmBox.class.getResource("ConfirmBox.fxml"));
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
}
