package github.weichware10.toolbox.gui.dialogs;

import github.weichware10.toolbox.Console;
import github.weichware10.util.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog zum Darstellen der Log Infos.
 */
public class LogDialog {
    /**
     * Startet den Dialog.
     */
    public LogDialog() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogDialog.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        LogDialogController controller = loader.getController();
        controller.setLog(Console.getLog());

        Dialog<Void> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setContent(root);
        dialog.show();
    }
}
