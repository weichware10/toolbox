package github.weichware10.toolbox.gui.dialogs;

import github.weichware10.util.Logger;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Zuständig für das das Design der Rückfrage.
 */
public class ConfirmDialog {

    private final String prompt;
    private final String icon;

    /**
     * Erstellt einen neuen ja/nein Dialog.
     *
     * @param prompt - Fragestellung
     * @param icon - zu verwendendes Icon, kann null sein
     */
    public ConfirmDialog(String prompt, String icon) {
        this.prompt = prompt;
        this.icon = icon;
    }

    /**
     * zeigt den Dialog an und gibt einen Boolean-Wert zurück.
     *
     * @return Konfirmationsboolean
     */
    public boolean getConfirmation() {
        FXMLLoader loader = new FXMLLoader(ConfirmDialog.class.getResource("ConfirmDialog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return false;
        }

        ConfirmDialogController controller = loader.getController();
        controller.setPrompt(prompt);

        Dialog<Boolean> dialog = new Dialog<>();
        // Icon und Grafik setzten
        if (icon != null) {
            Image image = null;
            try {
                image = new Image(icon);
                controller.setImage(image);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(image);
            } catch (Exception e) {
                Logger.error("error when loading ConfirmDialog Image", e);
            }
        }
        // Titel setzten
        dialog.setTitle(prompt);

        dialog.getDialogPane().setContent(root);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.YES) {
                return true;
            }
            return false;
        });

        Optional<Boolean> returnvalue = dialog.showAndWait();

        if (returnvalue.isPresent()) {
            return returnvalue.get();
        }

        return false;
    }
}
