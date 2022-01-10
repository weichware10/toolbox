package github.weichware10.toolbox.codecharts;

import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;

/**
 * Steuert den Datenverkehr und gibt die BEfehle zum anzeigen.
 * von Bild Raster und Eingabefenster.
 */
public class CodeChartsCoordinator {
    ConfigClient configClient;
    protected TrialData trialData;
    // private long[] speed = new long[2];
    private static boolean isRelative;
    private int[] dimensions = new int[2];
    private static String[] strings;
    private boolean configData;

    /**
     * Konstruktor für den CodeChartsCoordinator.
     *
     * @param configClient - aus dem die Configuration geladen wird
     */
    public CodeChartsCoordinator(ConfigClient configClient, TrialData trialData) {
        this.configClient = configClient;
        this.trialData = trialData;
        this.dimensions = configClient.getConfig().getCodeChartsConfiguration().getInitialSize();
    }

    public CodeChartsCoordinator() {

    }

    public int[] getDimensions() {
        return dimensions;
    }

    public String[] getStrings() {
        return strings;
    }

    public boolean getIsRelative() {
        return isRelative;
    }

    /**
     * Ruft alle Klassen und Funktionen in Reihenfolge auf in der CodeCharts durchgeführt wird.
     * Holt Daten aus der Config
     */
    public void startCodeCharts() {
        loadConfigData();
        if (configData == false) {
            errorMessage();
        }
        // CodeChartsBild bild = new CodeChartsBild("location");
        // Speichert location in Data für Berechnung in Raster
        // CodeChartsRaste´´r raster = new CodeChartsRaster();
        // CodeChartsEingabefenster fenster = new CodeChartsEingabefenster();
        // bild.show();
        // wait(speed[0]);
        // bild.hide();
        // raster.show();
        // wait(speed[1]);
        // String string = fenster.show();
        // raster.setInput(string);
        // ...
        // raster.sendData();
    }

    /**
     * Gibt Fehlermeldung bei falscher Config data.
     */
    private void errorMessage() {
        ;
    }

    /**
     * Holt die Daten aus der Config.
     * Speichert die Daten.
     *
     * @return gibt zurück ob fertig geladen.
     */
    protected boolean loadConfigData() {
        // if (data != vollständig) {
        //     configData = false;
        //     return configData;
        // } else {
        //     configData = true;
        // }
        // speed => Umrechnung von float zu long
        // speed[0] = aus Config;
        // speed[1] = aus Config;
        // dimensions[0] = aus Config;
        // dimensions[1] = aus Config;
        // isRelative = aus Cofig;
        return configData;
    }
}
