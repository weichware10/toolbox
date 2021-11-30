package github.weichware10.toolbox.codecharts;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet die Eingabe Klasse.
 */
public class CodeChartsEingabefensterTest {

    /**
     * Überprüft ob der Input korrket zurückgegeben wird.
     */
    @Test
    public void inputIsRecordedCorrectly() {
        CodeChartsCoordinator coordinator = new CodeChartsCoordinator();
        CodeChartsEingabefenster testfenster = new CodeChartsEingabefenster(coordinator);
        String input = "owls are cute";
        String output = testfenster.show();
        // Eingabe simulieren
        assertEquals(String.format("Output should match '%s'", input), input, output);
    }

    /**
     * Testet die Überprüfug des Inputs.
     */
    @Test
    @Ignore
    public void inputValidationShouldWork() {
        CodeChartsCoordinator coordinator = new CodeChartsCoordinator();
        CodeChartsEingabefenster testfenster = new CodeChartsEingabefenster(coordinator);
        testfenster.show();
        // Falsche Eingabe simulieren, Fehlermeldung erwarten
        // Richtige Eingabe simulieren, keine Fehlermeldung erwarten
        // Kann noch nicht konkret getestet werden.
    }
}
