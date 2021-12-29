package github.weichware10.toolbox.codecharts;

/**
 * Zeigt das Eingabefenster an und nimmt den Stringinput.
 * Überprüft den String auf Korrektheit.
 */
@SuppressWarnings("unused")
public class CodeChartsEingabefenster {
    private final CodeChartsCoordinator coordinator;
    private boolean button;
    private String[] strings;
    private String string;

    public CodeChartsEingabefenster(CodeChartsCoordinator coordinator) {
        this.coordinator = coordinator;
        strings = coordinator.getStrings();
    }

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
