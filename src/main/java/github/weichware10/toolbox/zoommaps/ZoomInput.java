package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * Nimmt Systeminput bzgl. der Koordinaten entgegen und verarbeitet sie
 * zu rawInput (int[2]).
 */
public class ZoomInput implements EventHandler<InputEvent> {

    ZoomCalculator zoomCalculator;

    public ZoomInput(ZoomCalculator zoomCalculator) {
        this.zoomCalculator = zoomCalculator;
    }

    /**
     * Nimmt Systeminput bei Klicken der Maustasten entgegen.
     */
    @Override
    public void handle(InputEvent event) {
        if (event instanceof MouseEvent) {
            handleClick((MouseEvent) event);
        } else if (event instanceof ScrollEvent) {
            handleScroll((ScrollEvent) event);
        }
    }

    /**
     * handlet clicks.
     *
     * @param mouseEvent -
     */
    private void handleClick(MouseEvent mouseEvent) {
        Logger.debug("click");
        int direction = -1;
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            direction = 1;
        }
        zoomCalculator.processInput(new Point2D(mouseEvent.getX(), mouseEvent.getY()), direction);
    }

    /**
     * handlet scrolls.
     *
     * @param scrollEvent -
     */
    public void handleScroll(ScrollEvent scrollEvent) {
        int direction = -1;
        if (scrollEvent.getDeltaY() < 0) {
            direction = 1;
        }
        zoomCalculator.processInput(new Point2D(scrollEvent.getX(), scrollEvent.getY()), direction);
    }

}
