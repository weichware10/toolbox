package github.weichware10.toolbox.eyetracking;

import github.weichware10.util.ToolType;
import github.weichware10.util.Tutorial;
import github.weichware10.util.config.ConfigClient;

/**
 * Bestimmung der Blickkoordinaten und Abspeicherung der Versuchsdaten.
 */
public class EyeTrackingTutorial extends Tutorial {

    public EyeTrackingTutorial(ConfigClient configClient) {
        super(configClient, ToolType.EYETRACKING);
    }

    /**
     * Startet das Tutorial und die Grundeinrichtung.
     */
    public void tutorial() {
        ; // noch nichts implementiert
    }
}
