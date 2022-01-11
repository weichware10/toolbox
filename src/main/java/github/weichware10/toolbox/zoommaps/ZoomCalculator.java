package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * Bekommt Rohdaten des Systems (Mausklick), berechnet neue Position des Bildes
 * und meldet Speichererfolg.
 */
@SuppressWarnings("unused")
public class ZoomCalculator {
    private double speed;
    private Point3D position;
    private TrialData data;
    ConfigClient configClient;
    ZoomMapsController controller;
    ZoomImage zoomImage;
    final String question;
    final String imageUrl;

    /**
     * Erstelt einen neues ZoomCalculator.
     *
     * @param data - ein (leeres) {@link TrialData}-Objekt
     * @param configClient - der ConfigClient der App
     * @param controller - die ControllerKlasse der Szene
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     * @throws MalformedURLException
     */
    public ZoomCalculator(TrialData data, ConfigClient configClient, ZoomMapsController controller)
            throws MalformedURLException, IllegalArgumentException, FileNotFoundException,
            IOException {
        this.data = data;
        this.configClient = configClient;
        this.controller = controller;
        imageUrl = configClient.getConfig().getImageUrl();
        question = configClient.getConfig().getQuestion();
        speed = configClient.getConfig().getZoomMapsConfiguration().getSpeed();
        controller.setQuestion(question);
        zoomImage = new ZoomImage(imageUrl, controller.getImageView(), this);
        position = new Point3D(zoomImage.imageSize[0] / 2, zoomImage.imageSize[1] / 2, 0);
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
        Point2D img = zoomImage.getImageCoordinates(raw);
        Rectangle2D viewport = zoomImage.move(img, direction * speed);
        data.addDataPoint(viewport);
    }
}
