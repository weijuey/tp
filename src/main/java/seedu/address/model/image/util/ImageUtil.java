package seedu.address.model.image.util;

import javafx.stage.FileChooser;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.image.ImageDetails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImageUtil {
    private static final FileChooser.ExtensionFilter IMAGE_FILE_FILTERS = new FileChooser.ExtensionFilter(
            "Image Files", "*.png", "*.jpg");
    private static FileChooser fileChooser;


    /**
     * Opens a FileChooser configured to view and select only image files.
     * @return the FileChooser that can be opened
     */
    public static FileChooser openImageFileChooser() {
        if (fileChooser == null) {
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(IMAGE_FILE_FILTERS);
        }
        return fileChooser;
    }

    /**
     * Checks if the {@code file} exists in the {@code directoryDirectory}.
     * @param file the file to check
     * @param directoryPath path of the directory to check if file exists within
     * @return true if the file exists
     */
    public static boolean fileExists(File file, Path directoryPath) {
        File directory = directoryPath.toFile();
        File[] directoryFiles = directory.listFiles();

        if (directoryFiles == null) {
            // Directory doesn't exist yet
            return false;
        }

        for (File f : directoryFiles) {
            if (f.getName().equals(file.getName())) {
                return true;
            }
        }

        return false;
    }

    public static ImageDetails copyTo(File src, Path destPath) throws IOException {
        BufferedImage srcImg = ImageIO.read(src);

        FileUtil.createIfMissing(destPath);
        File destFile = destPath.toFile();

        String imgExtension = getImageExtension(src.getName());

        ImageIO.write(srcImg, imgExtension, destFile);
        return new ImageDetails(destFile);
    }

    private static String getImageExtension(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[splitFileName.length -1];
    }
}
