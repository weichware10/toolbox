package github.weichware10.toolbox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Toolbox GUI.
 *
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ToolBox");

        // ----------Startbildschirm----------
        TextField trialIdInput = new TextField();
        trialIdInput.setPromptText("insert TrialID");

        Button startTestButton = new Button("Start");
        startTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test started");
            }
        });

        Label trialIdExplenation = new Label("Insert the ID given in the Email");

        VBox layoutTestIdWindow = new VBox(10);
        layoutTestIdWindow.setPadding(new Insets(10, 10, 10, 10));
        layoutTestIdWindow.getChildren().addAll(trialIdExplenation, trialIdInput, startTestButton);
        layoutTestIdWindow.setAlignment(Pos.CENTER);

        Scene toolBoxHome = new Scene(layoutTestIdWindow, 400, 400);

        // ----------end----------
        // Event welches beim schließen eines Fensters aufgerufen wird
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Wir kümmern uns selber um das schließen
                event.consume();
                // Fenster schließen, ja oder nein?
                closeProgramm(primaryStage);
            }
        });
        primaryStage.setScene(toolBoxHome);
        primaryStage.show();
    }

    //Nachfrage ob Programm wirklich beendet werden soll
    private void closeProgramm(Stage window) {
        boolean answer = ConfirmBox.display();
        if (answer) {
            window.close();
        }
    }
}
