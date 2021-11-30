package github.weichware10.toolbox.eyetracking;

import org.junit.Ignore;
import org.junit.Test;


public class EyeCalcTest {
    
    /**
     * Überprüft die Funktion ob Distanz realistisch und nutzbar eingegeben ist.
     */
    @Test
    @Ignore
    public void isWrongDistanceDetected() {
        /**
         * Benutzer übergibt Distanz zw Auge und Bildschirm
         * -> Falsche Distanz führt zu Error / redo
         * -> Richtige Distanz wird akzeptiert
         * 
         * Test kann noch nicht implementiert werden da Benutzereingaben implementiert werden müssen.
         */
        
    }


}




/**
 * prüfe ob 
 *      funktion start aufrufbar.
 *      distanz realistisch ist (>10 cm) //usergiven distance wird überprüft -> simulierte Nutzereingabe + überprüfung
 *      utility geladen werden kann.
 *      Auge sinnvolle werte zurück gibt   //schwierig automatisiert zu testen
 *      geladenes Grid Bildschirmgrösse entspricht.
 *      tutorial geladen und ausgeführt werden kann.
 *      calculate Zielkoordinaten richtig ausgibt.
 *      checkbox richtig geklickt wird bzw eingabe akzeptiert wird
 * 
 */