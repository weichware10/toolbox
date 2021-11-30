package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.config.ConfigClient;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Testet ZoomTutorial.
 */
public class ZoomTutorialTest {

    /**
     * Testen, ob die Einstellungen des configClient in Betracht genommen wird.
     * D.h. ob das Tutorial angezeigt wird.
     */
    @Test
    @Ignore
    public void tutorialBooleanShouldBeRespected() {
        ConfigClient configClient = new ConfigClient();
        configClient.loadConfiguration("www.weichware10.com/config");
        ZoomTutorial testsubject = new ZoomTutorial(configClient);
        configClient.getConfig().getCodeChartsConfiguration().setTutorial(true);
        testsubject.start();
        // Überprüfen, ob Tutorial angezeigt wird, da der Config-Wert true ist
        configClient.getConfig().getCodeChartsConfiguration().setTutorial(false);
        testsubject.start();
        // Überprüfen, ob Tutorial nicht angezeigt wird, da der Config-Wert false ist
    }
}
