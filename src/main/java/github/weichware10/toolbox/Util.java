package github.weichware10.toolbox;

import github.weichware10.util.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;

/**
 * util.
 */
public final class Util {
    // TODO: in util verschieben
    public static String tmpdir = null;

    /**
     * erstellt einen temporären Ordner.
     */
    public static String createTempDir() {
        String tmpdir = null;
        try {
            tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();
            Logger.info("created tempdir: " + tmpdir);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tmpdir;
    }

    /**
     * speichert ein Bild von der URL im temp Ordner.
     *
     * @param imageUrl - URL vom Bild
     * @return Pfad zum Bild
     */
    public static String saveImage(String imageUrl) {
        if (tmpdir == null) {
            tmpdir = createTempDir();
        }
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            Logger.error("MalformedURLException while setting image URL", e, true);
            return null;
        }
        String fileName = url.getFile();
        String destName = tmpdir + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(destName);

        try {
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destName);
            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            Logger.error("FileNotFoundException while saving Image", e, true);
            return null;
        } catch (IOException e) {
            Logger.error("IOException while saving Image", e, true);
            return null;
        }

        return destName;
    }

    /**
     * Löscht angelegten temporären Ordner beim Beenden der App.
     */
    public static void deleteTempDirHooker() {
        Thread deleterHook = new Thread(() -> {
            try {
                Logger.info("delete tmp folder");
                FileUtils.deleteDirectory(new File(tmpdir));
            } catch (IOException e) {
                Logger.error("IOException while deleting tmpdir", e, true);
            } catch (IllegalArgumentException e) {
                Logger.error("IllegalArgumentException while deleting tmpdir", e, true);
            }
        });
        Runtime.getRuntime().addShutdownHook(deleterHook);
    }
}
