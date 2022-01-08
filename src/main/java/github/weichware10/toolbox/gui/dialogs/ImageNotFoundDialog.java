package github.weichware10.toolbox.gui.dialogs;

import github.weichware10.util.Logger;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialogfenster, wenn Bild nicht geladen werden kann.
 */
public class ImageNotFoundDialog {

    /**
     * Dialogfenster, wenn Bild nicht geladen werden kann.
     *
     * @param error - Errornachicht
     */
    public void showImageNotFoundDialog(Exception error) {
        Logger.info("showImageNotFoundDialog");

        Dialog<Void> dialog = new Dialog<>();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.setTitle("Image can not be loaded");

        FXMLLoader loader = new FXMLLoader(
                ImageNotFoundDialog.class.getResource("ImageNotFoundDialog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

        ImageNotFoundDialogController controller = loader.getController();
        controller.setErrorMessage(error.getMessage());

        dialog.getDialogPane().setContent(root);

        dialog.showAndWait();
    }
}
