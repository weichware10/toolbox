package github.weichware10.toolbox.codecharts;

import github.weichware10.util.config.ConfigClient;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet CodeChartsTutorial.
 */

public class CodeChartsTutorialTest {
    /**
     * Testen, ob die Einstellungen des configClient in Betracht genommen wird.
     * D.h. ob das Tutorial angezeigt wird.
     */
    @Test
    @Ignore
    public void tutorialBooleanShouldBeRespected() {
        ConfigClient configClient = new ConfigClient();
        configClient.loadFromDataBase("www.weichware10.com/config");
    }
}
