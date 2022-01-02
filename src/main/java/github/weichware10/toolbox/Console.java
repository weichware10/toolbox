package github.weichware10.toolbox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javafx.scene.control.TextArea;

// TODO: in util verschieben
// TEMPORÄR - wird in util besser
/**
 * Loggen in Konsole.
 */
public class Console {

    private static PrintStream logfile;
    private static PrintStream sysOut;
    private static TextArea logArea;
    public final static PrintStream logStream = new PrintStream(new LogStream(), true);

    /**
     * sets logfile.
     *
     * @param filename - the file
     */
    public static void setLogfile(String filename) {
        if (filename == null) {
            return;
        }
        try {
            File file = new File(filename);
            file.createNewFile();
            logfile = new PrintStream(file);
        } catch (IOException e) {
            logfile = null;
        }
    }

    public static void setLogArea(TextArea logArea) {
        Console.logArea = logArea;
    }

    public static void setSysOut(PrintStream out) {
        Console.sysOut = out;
    }

    private static class LogStream extends OutputStream {
        @Override
        public void write(int i) {
            if (logArea != null) {
                logArea.appendText(String.valueOf((char) i));
            }
            if (sysOut != null) {
                sysOut.append((char) i);
            }
            if (logfile != null) {
                logfile.append((char) i);
            }
        }
    }
}
