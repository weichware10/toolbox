package github.weichware10.toolbox.zoommaps;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Nimmt Systeminput bzgl. der Koordinaten entgegen und verarbeitet sie
 * zu rawInput (int[2]).
 */
public class ZoomInputClick implements EventHandler<MouseEvent> {

    ZoomCalculator zoomCalculator;

    public ZoomInputClick(ZoomCalculator zoomCalculator) {
        this.zoomCalculator = zoomCalculator;
    }

    /**
     * Nimmt Systeminput bei Klicken der Maustasten entgegen.
     */
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            zoomCalculator.processInputIn(new double[]{ mouseEvent.getX(), mouseEvent.getY() });
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            zoomCalculator.processInputOut(new double[]{ mouseEvent.getX(), mouseEvent.getY() });
        }
    }
}
