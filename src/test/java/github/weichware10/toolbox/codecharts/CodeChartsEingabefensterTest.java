package github.weichware10.toolbox.codecharts;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * TODO: David.
 */
public class CodeChartsEingabefensterTest {

    /**
     * TODO: was macht der Test.
     */
    @Test
    public void inputIsRecordedCorrectly() {
        CodeChartsEingabefenster testfenster = new CodeChartsEingabefenster();
        String input = "owls are cute";
        String output = testfenster.show();
        // Eingabe simulieren
        assertEquals(String.format("Output should match '%s'", input), input, output);
    }

    @Test
    @Ignore
    public void inputValidationShouldWork() {
        CodeChartsEingabefenster testfenster = new CodeChartsEingabefenster();
        testfenster.show();
        // Falsche Eingabe simulieren, Fehlermeldung erwarten
        // Richtige Eingabe simulieren, keine Fehlermeldung erwarten
        // Kann noch nicht konkret getestet werden.
    }
}
