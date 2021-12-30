package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.ToolType;
import github.weichware10.util.Tutorial;
import github.weichware10.util.config.ConfigClient;

/**
 * Zeigt das Tutorial ZoomBild in einem allgemeinen Tutorialfenster.
 */
public class ZoomTutorial extends Tutorial {

    /**
     * Instanziiert ein neues ZoomTutorial.
     *
     * @param configClient - Der Config Client für die Einstellungen.
     */
    public ZoomTutorial(ConfigClient configClient) {
        super(configClient, ToolType.ZOOMMAPS);
    }

    @Override
    protected void tutorial() {
        ;
    }
}
