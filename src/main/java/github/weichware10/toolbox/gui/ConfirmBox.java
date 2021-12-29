package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Kümmert sich um das Design der Rückfrage.
 */
public class ConfirmBox {
    /**
     * Die eigentliche Funktion, die den Startbildschirm darstellt.
     *
     * @param prompt - Fragestellung
     */
    public static boolean display(String prompt) {
        FXMLLoader loader = new FXMLLoader(ConfirmBox.class.getResource("ConfirmBox.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Logger.error("error when loading confirm box", e);
            return false;
        }

        ConfirmBoxController controller = loader.getController();
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
        Logger.info(returnvalue.toString());


        if (returnvalue.isPresent()) {
            return returnvalue.get();
        }

        return false;
    }
}
