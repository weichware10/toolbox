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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Toolbox GUI
 *
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ToolBox");
        
        Label trialIdExplenation = new Label("Insert the ID given in the Email");

        TextField  trialIdInput = new TextField();
        trialIdInput.setPromptText("insert TrialID");

        Button startTestButton = new Button("Start");
        startTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test started");
            }
        });

        VBox LayoutTestIdWindow = new VBox(10);
        LayoutTestIdWindow.setPadding(new Insets(10, 10, 10, 10));
        LayoutTestIdWindow.getChildren().addAll(trialIdExplenation, trialIdInput, startTestButton);
        LayoutTestIdWindow.setAlignment(Pos.CENTER);

        Scene toolBoxHome = new Scene(LayoutTestIdWindow, 400, 400);

        //----------end----------
        //set the action after the window got closed
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event){
                //We ourself take care for the closing
                event.consume();
                //Close window with yes or no
                closeProgramm(primaryStage);
            }
        });
        primaryStage.setScene(toolBoxHome);
        primaryStage.show();
    }

    private void closeProgramm(Stage window){
        boolean answer = ConfirmBox.display("End the programm", "Sure you want to quit ?");
        if(answer){
            window.close();
        }
    }
}
