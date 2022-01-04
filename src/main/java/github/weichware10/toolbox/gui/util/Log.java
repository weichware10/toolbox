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

    private static SimpleBooleanProperty visibleProperty = new SimpleBooleanProperty(false);
    public static ObservableBooleanValue visible = visibleProperty;

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
        return visible.get();
    }

    public static void show() {
        logStage.show();
        visibleProperty.set(true);
    }

    public static void hide() {
        logStage.hide();
        visibleProperty.set(false);
    }

    public static void close() {
        logStage.close();
    }

    /**
     * Loggt den Inhalt (falls vorhanden) mit dem gegebenen Typ.
     *
     * @param content - zu loggender Inhalt
     * @param type - "DEBUG", "INFO", "WARN" oder "ERROR"
     */
    public static void log(String content, String type) {
        if (content.length() == 0) {
            return;
        }
        switch (type) {
            case "DEBUG":
                Logger.debug(content);
                break;
            case "INFO":
                Logger.info(content);
                break;
            case "WARN":
                Logger.warn(content);
                break;
            case "ERROR":
                Logger.error(content);
                break;
            default:
                break;
        }
    }
}
