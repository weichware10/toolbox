package github.weichware10.toolbox.zoommaps;

import javafx.geometry.Point2D;
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

    public final double[] imageSize;
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
        imageSize = new double[] { image.getWidth(), image.getHeight() };
        imageView.setImage(image);
        Rectangle2D viewport = new Rectangle2D(0, 0, imageSize[0], imageSize[1]);
        imageView.setViewport(viewport);
        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new ZoomInput(zoomCalculator));
        imageView.addEventFilter(ScrollEvent.SCROLL, new ZoomInput(zoomCalculator));
        imageView.getImage();
        // imageView.addEventFilter(MouseEvent., eventFilter);
    }

    /**
     * Berechnet die Bildkoordinaten von ImageView Koordinaten.
     *
     * @param imageViewCoordinates -
     * @return Bildkoordinaten
     */
    public Point2D getImageCoordinates(Point2D imageViewCoordinates) {
        Rectangle2D viewport = imageView.getViewport();
        double widthFactor = imageViewCoordinates.getX()
                / imageView.getBoundsInLocal().getWidth();
        double heightFactor = imageViewCoordinates.getY()
                / imageView.getBoundsInLocal().getHeight();
        return new Point2D(viewport.getMinX() + viewport.getWidth() * widthFactor,
                viewport.getMinY() + viewport.getHeight() * heightFactor);
    }

    /**
     * Bewegt das Bild an die gegebenen Koordinaten, neue
     * Koordinaten werden zurückgegeben.
     *
     * @param position - gegebene Koordinaten (float[3])
     * @return neue Koordinaten
     */
    protected Rectangle2D move(Point2D position, double speed) {
        int minPixels = 25;
        Rectangle2D viewport = imageView.getViewport();

        double scale = clamp(Math.pow(1.01, speed),

                Math.min(minPixels / viewport.getWidth(), minPixels / viewport.getHeight()),

                Math.max(imageSize[0] / viewport.getWidth(), imageSize[1] / viewport.getHeight())

        );

        Point2D mouse = new Point2D(position.getX(), position.getY());

        double newWidth = viewport.getWidth() * scale;
        double newHeight = viewport.getHeight() * scale;

        double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                0, imageSize[0] - newWidth);
        double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                0, imageSize[1] - newHeight);

        Rectangle2D newViewport = new Rectangle2D(newMinX, newMinY, newWidth, newHeight);
        imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
        return newViewport;
    }

    private double clamp(double value, double min, double max) {

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
