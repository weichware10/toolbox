package github.weichware10.toolbox.gui.dialogs;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Kümmert sich um das Design der Rückfrage.
 */
public class ConfirmDialog {

    private final String prompt;

    /**
     * Erstellt einen neuen ja/nein Dialog.
     *
     * @param prompt - Fragestellung
     */
    public ConfirmDialog(String prompt) {
        this.prompt = prompt;
    }

    /**
     * zeigt den Dialog an und gibt einen Boolean-wert zurück.
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

        // ButtonType okButtonType = new ButtonType("OK", );
        Dialog<Boolean> dialog = new Dialog<>();
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
