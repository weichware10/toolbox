package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;

/**
 * Bekommt Rohdaten des Systems (Mausklick), berechnet neue Position des Bildes
 * und meldet Speichererfolg.
 */
@SuppressWarnings("unused")
public class ZoomCalculator {
    private float speed;
    private Point3D position;
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
        position = new Point3D(zoomBild.imageSize[0] / 2, zoomBild.imageSize[1] / 2, 0);
    }

    /**
     * verarbeitet Input von Benutzer/System.
     *
     * @param raw - Screenkoordinaten vom Mausklick (int[2])
     */
    public void processInput(Point2D raw, int direction) {
        if (Math.abs(direction) != 1) {
            throw new IllegalArgumentException("direction has not be of magnitude 1");
        }
        Point2D img = zoomBild.getImageCoordinates(raw);
        Logger.debug(raw.toString() + " -> " + img.toString());
        position = zoomBild.move(img, direction * speed);
        data.addDataPoint(new int[]{ (int) position.getX(), (int) position.getY() }, (int) position.getZ());
    }
}
