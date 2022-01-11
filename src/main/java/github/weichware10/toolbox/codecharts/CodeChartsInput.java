package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import info.debatty.java.stringsimilarity.Levenshtein;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

/**
 * Zeigt das Eingabefenster an und nimmt den Stringinput.
 * Überprüft den String auf Korrektheit.
 */
public class CodeChartsInput {
    private final CodeChartsInputController controller;
    private final BorderPane root;

    private static final int LOWEST_ALLOWED_DISTANCE = 5;

    /**
     * Initialisiert ein neuen CodeChartsInput-Dialog.
     */
    public CodeChartsInput() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeChartsInput.fxml"));

        BorderPane root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }
        this.root = root;

        controller = loader.getController();
    }

    /**
     * Führt den Dialog aus und verarbeitet den Input.
     */
    public String getInput(List<String> currentStrings, int currentIteration, int iterations) {

        // speicher Ergebnis
        final SimpleStringProperty result = new SimpleStringProperty(null);

        controller.progressBar.setProgress((double) currentIteration / (double) iterations);
        controller.bottomText.setText(
            String.format("Durchlauf %d/%d", currentIteration, iterations));
        // Text von vorherigen Durchläufen löschen
        controller.inputField.clear();
        controller.suggestionText.setText("");

        // Dialog erstellen
        Dialog<Void> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(root);

        // Buttons erstellen und hinzufügen
        ButtonType submitButtonType = new ButtonType("Abgeben", ButtonData.OK_DONE);
        ButtonType noAnswerButtonType = new ButtonType("Überspringen", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, noAnswerButtonType);

        Button submitButton = (Button) dialog.getDialogPane().lookupButton(submitButtonType);
        // Ausführung bei Drücken des Submit Buttons
        submitButton.addEventFilter(ActionEvent.ACTION, e -> {
            String input = controller.inputField.getText();
            // kein Input
            if (input.length() == 0) {
                controller.suggestionText.setText("Please provide an input.");
                e.consume();
                return;
            }
            // Input stimmt mit einem String überein
            if (currentStrings.contains(input)) {
                result.set(input);
                return;
            }

            // Problembehandlung
            e.consume();
            // sortieren der Strings nach kürzester Levenshtein-Distanz zum Input
            LevenshteinComparator comparator = new LevenshteinComparator(input);
            List<String> sortedStrings = currentStrings.stream()
                    .sorted(comparator).collect(Collectors.toList());

            // Bei aktzeptabler Distanz Vorschlag äußern
            if (comparator.getLowestDistance() <= LOWEST_ALLOWED_DISTANCE) {
                controller.suggestionText.setText(
                        String.format("Meinten Sie \"%s\"?", sortedStrings.get(0)));
            } else {
                controller.suggestionText.setText("""
                        Dieses Wort gibt es nicht.
                        Versuchen Sie es erneut oder klicken Sie auf \"Überspringen\"
                        - Der aktuelle Durchlauf wird dadurch wiederholt.""");
            }

        });

        // ENTER auf Button setzen
        controller.inputField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                submitButton.fire();
            }
        });

        dialog.showAndWait();
        return result.get();
    }

    /**
     * Vergleicht 2 Strings aufgrund ihrer Ähnlichkeit mit einem Dritten.
     */
    private class LevenshteinComparator implements Comparator<String> {

        private final String base;
        private final Levenshtein levenshtein = new Levenshtein();
        private int lowestDistance = Integer.MAX_VALUE;

        public LevenshteinComparator(String base) {
            this.base = base;
        }

        public int getLowestDistance() {
            return lowestDistance;
        }

        @Override
        public int compare(String str1, String str2) {
            int distance1 = (int) levenshtein.distance(base, str1);
            int distance2 = (int) levenshtein.distance(base, str2);
            lowestDistance = (distance1 < lowestDistance) ? distance1 : lowestDistance;
            lowestDistance = (distance2 < lowestDistance) ? distance2 : lowestDistance;
            return distance1 - distance2;
        }
    }
}
