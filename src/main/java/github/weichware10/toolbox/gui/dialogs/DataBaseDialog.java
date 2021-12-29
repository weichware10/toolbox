package github.weichware10.toolbox.gui.dialogs;

import github.weichware10.util.Logger;
import github.weichware10.util.db.DataBaseClient;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog zum Ändern des Datenbankzugriffs.
 */
public class DataBaseDialog {

    private DataBaseClient dataBaseClient = null;

    /**
     * zeigt einen Dialog an und gibt einen neuen {@link DataBaseClient} zurück.
     *
     * @return den neuen DataBaseClient, oder null falls nicht erfolgreich
     */
    public DataBaseClient getDataBaseClient() {
        Dialog<Void> dialog = new Dialog<>();
        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        FXMLLoader loader = new FXMLLoader(DataBaseDialog.class.getResource("DataBaseDialog.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Logger.error("Error while displaying dialog", e);
        }
        if (root == null) {
            return null;
        }

        // controller to access fields
        DataBaseDialogController controller = loader.getController();
        // nur bei Erfolg Dialog verlassen
        final Button ok = (Button) dialog.getDialogPane().lookupButton(okButtonType);
        ok.addEventFilter(ActionEvent.ACTION, okEvent -> {
            try {
                // Versuchen Datenbankclient zu erstellen
                dataBaseClient = new DataBaseClient(
                        controller.getUrl(),
                        controller.getUsername(),
                        controller.getPassword(),
                        controller.getSchema());
            } catch (IllegalArgumentException e) {
                // display error
                Logger.info("Error while changing database connection");
                controller.setWarning("Your input is not valid:");
                controller.setError(e.getMessage());
                okEvent.consume();
            }
        });
        dialog.getDialogPane().setContent(root);
        dialog.showAndWait();
        return dataBaseClient;
    }
}
