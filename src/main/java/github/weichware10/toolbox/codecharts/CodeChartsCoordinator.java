package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Data;

/**
 * TODO:
 * Daten in loadConfigData auf Variablen speichern.
 */
public class CodeChartsCoordinator {
    private Data data;
    private long[] speed = new long[2];
    private static boolean isRelative;
    private static int[] dimensions = new int[2];
    private static String[] strings;
    private boolean configData;

    public static int[] getDimensions() {
        return dimensions;
    }

    public static String[] getStrings() {
        return strings;
    }

    public static boolean getIsRelative() {
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
        // CodeChartsBild.show();
        // wait(speed[0]);
        // CodeChartsBild.hide();
        // CodeChartsRaster.show();
        // wait(speed[1]);
        // CodeChartsEingabefenster.show();
        // ...
        CodeChartsRaster.sendData();
    }

    /**
     * Gibt Fehlermeldung bei falscher Config data.
     */
    private void errorMessage() {
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
        // speed[0] = aus Config;
        // speed[1] = aus Config;
        // dimensions[0] = aus Config;
        // dimensions[1] = aus Config;
        // isRelative = aus Cofig;
        return configData;
    }
}

