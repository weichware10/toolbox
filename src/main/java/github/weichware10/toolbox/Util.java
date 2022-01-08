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
import java.util.Arrays;
import java.util.List;
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
     * @throws MalformedURLException
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String saveImage(String imageUrl) throws MalformedURLException,
            IllegalArgumentException, FileNotFoundException, IOException {
        if (tmpdir == null) {
            tmpdir = createTempDir();
        }
        URL url = null;
        url = new URL(imageUrl);

        String fileName = url.getFile();
        String destName = tmpdir + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(destName);

        String fileType = fileName.substring(fileName.lastIndexOf("."));

        List<String> supportedFileTypes = Arrays.asList(".jpg", ".png", ".jpeg");

        if (!supportedFileTypes.contains(fileType)) {
            throw new IllegalArgumentException("Filetype of given image is not supported");
        }

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destName);
        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();

        return destName;
    }

    /**
     * Löscht angelegten temporären Ordner beim Beenden der App.
     */
    public static void deleteTempDir() {
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
