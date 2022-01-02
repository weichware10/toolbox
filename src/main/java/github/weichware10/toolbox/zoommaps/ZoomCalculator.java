package github.weichware10.toolbox.zoommaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import javafx.scene.image.ImageView;

/**
 * Bekommt Rohdaten des Systems (Mausklick), berechnet neue Position des Bildes
 * und meldet Speichererfolg.
 */
@SuppressWarnings("unused")
public class ZoomCalculator {
    private float speed;
    private float[] position;
    private TrialData data;
    ConfigClient configClient;
    ZoomMapsController controller;
    ZoomBild zoomBild;
    final List<String> questions;
    final List<String> imageUrls;

    /**
     * Erstelt einen neues ZoomCalculator.
     *
     * @param data -
     * @param configClient -
     * @param controller -
     */
    public ZoomCalculator(TrialData data, ConfigClient configClient,
            ZoomMapsController controller) {
        this.data = data;
        this.configClient = configClient;
        this.controller = controller;
        imageUrls = configClient.getConfig().getZoomMapsConfiguration().getImageUrls();
        questions = new ArrayList<String>();
        speed = configClient.getConfig().getZoomMapsConfiguration().getSpeed();
        for (int i = 0; i < imageUrls.size(); i++) {
            questions.add(configClient.getConfig().getQuestion());
        }
        loadState(questions.get(0), imageUrls.get(0));
    }

    private void loadState(String question, String imageUrl) {
        controller.setQuestion(question);
        zoomBild = new ZoomBild(imageUrl, controller.getImageView(), this);
    }

    /**
     * verarbeitet Input von Benutzer/System.
     *
     * @param raw - Screenkoordinaten vom Mausklick (int[2])
     */
    public void processInputIn(double[] raw) {
        Logger.debug("processInputIn");
    }

    /**
     * verarbeitet Input von Benutzer/System.
     *
     * @param raw - Screenkoordinaten vom Mausklick (int[2])
     */
    public void processInputOut(double[] raw) {
        Logger.debug("processInputOut");
    }

    /**
     * Sendet gesammelte Daten Speichermedium und meldet (Miss-)Erfolg.
     *
     * @return Erfolgsmeldung
     */
    public boolean save() {
        return false;
    }

    /**
     * Berechnet die neue position des Bildes aus den Rohdaten.
     *
     * @param rawInput - Screenkoordinaten vom Mausklick (int[2])
     * @return neue Screenkoordinaten
     */
    private float[] calculate(int[] rawInput) {
        // float[] output = new float[3];
        return new float[]{0, 0, 0};
    }
}
