package github.weichware10.toolbox.gui.dialogs;

import github.weichware10.util.Logger;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Dialogfenster, wenn ein Fehler auftritt.
 */
public class ErrorDialog {

    /**
     * Dialogfenster, wenn ein Fehler auftritt.
     *
     * @param error - Errornachicht
     */
    public void showErrorDialog(Exception error) {
        Logger.info("showing ErrorDialog...");

        Dialog<Void> dialog = new Dialog<>();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.setTitle("An error occured");

        FXMLLoader loader = new FXMLLoader(
                ErrorDialog.class.getResource("ErrorDialog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

        ErrorDialogController controller = loader.getController();

        // Icon setzten
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("error.png").toString()));

        controller.setErrorMessage(error.toString());

        dialog.getDialogPane().setContent(root);

        dialog.showAndWait();
    }
}
