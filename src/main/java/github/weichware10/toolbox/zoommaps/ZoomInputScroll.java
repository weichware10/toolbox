package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.ScrollEvent;

/**
 * Nimmt Systeminput bzgl. der Koordinaten entgegen und verarbeitet sie
 * zu rawInput (int[2]).
 */
public class ZoomInputScroll implements EventHandler<ScrollEvent> {

    ZoomCalculator zoomCalculator;

    public ZoomInputScroll(ZoomCalculator zoomCalculator) {
        this.zoomCalculator = zoomCalculator;
    }

    /**
     * Nimmt Systeminput bei Klicken der Maustasten entgegen.
     */
    public void handle(ScrollEvent scrollEvent) {
        Logger.debug(Double.toString(scrollEvent.getDeltaY()));
        if (scrollEvent.getDeltaY() > 0) {
            zoomCalculator.processInputIn(new double[]{ scrollEvent.getX(), scrollEvent.getY() });
        } else {
            zoomCalculator.processInputOut(new double[]{ scrollEvent.getX(), scrollEvent.getY() });
        }
    }
}
