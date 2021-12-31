package github.weichware10.toolbox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

// TODO: in util verschieben
/**
 * Loggen in Konsole.
 */
public class Console extends OutputStream {

    private static PrintStream logfile;
    private static PrintStream out;

    private static String log;

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

    public static void setOut(PrintStream out) {
        Console.out = out;
    }

    public static String getLog() {
        return log;
    }

    @Override
    public void write(int i) throws IOException {
        log += (char) i;
        out.append((char) i);
        if (logfile != null) {
            logfile.append((char) i);
        }
    }
}
