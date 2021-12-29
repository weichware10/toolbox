package github.weichware10.toolbox.codecharts;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet die Raster-Klasse.
 */
@SuppressWarnings("unused")
public class CodeChartsRasterTest {
    /**
     * testet ob der Input richtig gesetzt wurde.
     */
    @Test
    public void correctInputSet() {
        String input = "Hallo i bims";
        CodeChartsRaster testsubject = new CodeChartsRaster();
        testsubject.setInput(input);
        assertEquals(input, testsubject.getInput());
    }

    /**
     * testet ob die Daten richtig gesendet wurden.
     */
    @Test
    @Ignore
    public void validateSendData() {
        CodeChartsRaster testraster = new CodeChartsRaster();
        CodeChartsCoordinator testcoordinator = new CodeChartsCoordinator();
        // VErgleicht die Daten in Raster mit denen die an den Coordinator geschickt wurden.
    }

    /**
     * teste die Berechnung der Coordinaten.
     */
    @Test
    @Ignore
    public void correctCoordinates() {
        // Rastergröße und String werden in CodeChartsRaster gestezt.
        // nach Durchfühung von loadPastRuns wird die Koordiate
        // mit einer schon vorgegebenen korrekten Koordinate verglichen.
    }
}
