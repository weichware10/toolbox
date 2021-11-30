package github.weichware10.toolbox.codecharts;

import org.junit.Ignore;
import org.junit.Test;

/**
 * TODO: David.
 */
public class CodeChartsCoordinatorTest {
    
    /**
     * testet Datenspeicherung.
     */
    @Test
    @Ignore
    public void shouldSaveData() {
        ;
    }

    /**
     * testet das Speichern der Config Data.
     */
    @Test
    @Ignore
    public void saveConfigData() {
        CodeChartsCoordinator testsubject = new CodeChartsCoordinator();
        testsubject.startCodeCharts();
        // => Testet ob die Variablen nach Durchführung der Fkt.
        // Werte enthalten.
    }

    /**
     * testet die AUsgabe der Errormessage.
     */
    @Test
    @Ignore
    public void testErrorMessage() {
        CodeChartsCoordinator testsubject = new CodeChartsCoordinator();
        testsubject.startCodeCharts();
        // => testet mit verschiedenen boolean Werten bei isRelatve in Data
        // ob der Error gegeben wird.
    }
}
