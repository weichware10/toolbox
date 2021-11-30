package github.weichware10.toolbox.codecharts;

/**
 * TODO: Fertigstllen.
 */
public class CodeChartsEingabefenster {
    private boolean button;
    private String[] strings = CodeChartsCoordinator.getStrings();
    private String string;

    /**
     * Zeigt das Eingabefenster.
     * Startet alle darunterliegenden Funktionen.
     */
    public String show() {
        // checkButton();
        // boolean validate = validateString();
        // if (validate == false) {
        //     errorMessage();
        //      => Erneuter Eingabeaufruf
        // }
        return "owls are cute";
    }

    /**
     * Gibt fehlermeldung für inkorrekten String aus.
     */
    private void errorMessage() {
        ;
    }

    /**
     * Überprüft ob der Button gedrückt wurde.
     */
    private boolean checkButton() {
        // if (/*platzhalter*/) {
        //     button = true;
        // } else {
        //     button = false;
        // }
        return button;
    }

    /**
     * Überprüft die Richtigkeit des Strings.
     */
    private boolean validateString() {
        boolean checkString = false;
        for (String stringsComponent : strings) {
            if (string == stringsComponent) {
                checkString = true;
                return checkString;
            }
        }
        return checkString;
    }
}
