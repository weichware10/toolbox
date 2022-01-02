package github.weichware10.toolbox.gui.util;

import github.weichware10.util.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Dialog zum Darstellen der Log Infos.
 */
public class Log {

    private static Stage logStage;
    // private static boolean visible = false;

    private static boolean visible = false;
    public static ObservableBooleanValue isVisible = new SimpleBooleanProperty(visible);

    /**
     * Startet das Log.
     */
    public static void start() {
        FXMLLoader loader = new FXMLLoader(Log.class.getResource("Log.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        Scene logScene = new Scene(root, 750, 500);
        logStage = new Stage();
        logStage.setScene(logScene);
        logStage.setTitle("Toolbox - Log");
        logStage.setMinHeight(500);
        logStage.setMinWidth(550);
        logStage.getIcons().add(new Image("app-icon.png"));
        logStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            hide();
        });
    }

    public static boolean isVisible() {
        return visible;
    }

    public static void show() {
        logStage.show();
        visible = true;
    }

    public static void hide() {
        logStage.hide();
        visible = false;
    }

    public static void close() {
        logStage.close();
    }
}
