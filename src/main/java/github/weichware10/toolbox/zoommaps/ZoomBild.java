package github.weichware10.toolbox.zoommaps;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * Ändert den aktuellen Bildausschnitt mit Hilfe gegebener Koordianten
 * und gibt die neuen Koordinaten an ZoomCalculator zurück.
 */
public class ZoomBild {

    private double[] size;
    private ImageView imageView;

    /**
     * Instanziiert ein neues ZoomBild.
     *
     * @param location - die Quelle der zu benutzenden Bilddatei
     * @throws IllegalArgumentException falls die Location falsch ist
     */
    public ZoomBild(String location, ImageView imageView, ZoomCalculator zoomCalculator) {
        this.imageView = imageView;
        // TODO auf errors reagiern
        Image image = new Image(location);
        size = new double[] { image.getWidth(), image.getHeight() };
        imageView.setImage(image);
        // double[] imageViewSize = new double[] { imageView.getFitWidth(), imageView.getFitHeight() };
        Rectangle2D viewport = new Rectangle2D(0, 0, size[0], size[1]);
        imageView.setViewport(viewport);
        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new ZoomInputClick(zoomCalculator));
        imageView.addEventFilter(ScrollEvent.SCROLL, new ZoomInputScroll(zoomCalculator));
        // imageView.addEventFilter(MouseEvent., eventFilter);
    }

    /**
     * Bewegt das Bild an die gegebenen Koordinaten, neue
     * Koordinaten werden zurückgegeben.
     *
     * @param position - gegebene Koordinaten (float[3])
     * @return neue Koordinaten
     */
    protected float[] move(float[] position) {
        return new float[]{0, 0, 0};
    }
}
