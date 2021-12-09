package github.weichware10.toolbox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Funktion zur Abfrage, ob das Fenster wirklich geschlossen werden soll.
 */
public class ConfirmBox {

    static boolean answer;

    /**
     * Funktion zur Rücksicherung ob das Fenster wirklich geschlossen werden soll.
     *
     * @return - Gibt true zurück, wenn das Window geschlossen werden soll
     */
    public static boolean display() {
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("End the programm");
        window.setMinWidth(250);

        Label label1 = new Label();
        label1.setText("Sure you want to quit ?");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer = true;
                window.close();
            }
        });
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer = false;
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
