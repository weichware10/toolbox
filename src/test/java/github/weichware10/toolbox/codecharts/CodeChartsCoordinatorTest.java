package github.weichware10.toolbox.codecharts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet CodeChartsCoordinator.
 */
@SuppressWarnings("unused")
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
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     * @throws MalformedURLException
     */
    @Test
    @Ignore
    public void saveConfigData() throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        CodeChartsCoordinator coordinator = new CodeChartsCoordinator(null, null, null, null, null);
        // => Testet ob die Variablen nach Durchführung der Fkt.
        // Werte enthalten.
    }

    /**
     * testet die AUsgabe der Errormessage.
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     * @throws MalformedURLException
     */
    @Test
    @Ignore
    public void testErrorMessage() throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        CodeChartsCoordinator coordinator = new CodeChartsCoordinator(null, null, null, null, null);
        // => testet mit verschiedenen boolean Werten bei isRelatve in Data
        // ob der Error gegeben wird.
    }
}
