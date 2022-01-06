package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.gui.dialogs.ConfirmDialog;
import javafx.stage.WindowEvent;

/**
 * praktische Sachen für GUI.
 */
public class Util {
    /**
     * Filtert Anfragen das Fenster zu schließen
     * - wird das Event consumed, wird das Fenster nicht geschlossen.
     *
     * @param event - das WindowEvent mit der Anfrage
     */
    public static void closeRequestFilter(WindowEvent event) {
        String icon = Util.class.getResource("thonkang.png").toString();
        // Fenster schließen, ja oder nein?
        boolean confirmation = new ConfirmDialog("Do you want to close the window?", icon)
                .getConfirmation();
        // event consumieren -> nicht schließen
        if (!confirmation) {
            event.consume();
        }
    }
}