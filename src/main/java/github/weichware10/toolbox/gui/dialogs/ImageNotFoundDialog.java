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
 * Dialogfenster, wenn Bild nicht geladen werden kann.
 */
public class ImageNotFoundDialog {

    /**
     * Dialogfenster, wenn Bild nicht geladen werden kann.
     *
     * @param error - Errornachicht
     */
    public void showImageNotFoundDialog(Exception error) {
        Logger.info("showing ImageNotFoundDialog...");

        Dialog<Void> dialog = new Dialog<>();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.setTitle("Image could not be loaded");

        FXMLLoader loader = new FXMLLoader(
                ImageNotFoundDialog.class.getResource("ImageNotFoundDialog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

        ImageNotFoundDialogController controller = loader.getController();

        // set icon
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("noimage.png").toString()));

        controller.setErrorMessage(error.toString());

        dialog.getDialogPane().setContent(root);

        dialog.showAndWait();
    }
}
