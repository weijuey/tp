package seedu.address.model.image.util;

import javafx.stage.FileChooser;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {
    private static final String JPG_EXTENSION = "jpg";
    private static final String PNG_EXTENSION = "png";
    private static final FileChooser.ExtensionFilter IMAGE_FILE_FILTERS = new FileChooser.ExtensionFilter(
            "Image Files", "*." + PNG_EXTENSION, "*." + JPG_EXTENSION);

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
        // FileChooser should have already ensured that the file must have _some_ valid extension
        assert splitFileName.length > 1;

        String extension = splitFileName[splitFileName.length -1];
        // FileChooser should have already ensured that the file must have some _valid_ extension
        assert extension.equals(JPG_EXTENSION) || extension.equals(PNG_EXTENSION);

        return extension;
    }

    /**
     * Removes from this list images that no longer exist within the data/images directory.
     *
     * @return a sanitized {@code ImageDetailsList} object.
     */
    public static ImageDetailsList sanitizeList(ImageDetailsList listToSanitize) {
        List<ImageDetails> sanitizedList = new ArrayList<>();
        for (ImageDetails img : listToSanitize) {
            if (fileExists(img.getImageFile(), ImageDetails.CONTACT_IMAGES_PATH)) {
                sanitizedList.add(img);
            }
        }
        return new ImageDetailsList(sanitizedList);
    }

    public static void removeFile(ImageDetails imageToDelete) {
        Path filePath = imageToDelete.getImageFile().toPath();
        if (!FileUtil.isFileExists(filePath)) {
            return;
        }

       FileUtil.deleteFile(filePath);
    }
}
