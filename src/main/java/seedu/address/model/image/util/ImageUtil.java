package seedu.address.model.image.util;

import javafx.stage.FileChooser;
import seedu.address.commons.util.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public static List<File> copyTo(List<File> srcFiles, Path parentPath) throws IOException {
        List<File> destFiles = new ArrayList<>();
        for (File src : srcFiles) {
            BufferedImage srcImg = ImageIO.read(src);

            Path destPath = Paths.get(parentPath.toString(), src.getName());
            if (FileUtil.isFileExists(destPath)) {
                throw new FileAlreadyExistsException(destPath.getFileName().toString());
            }

            FileUtil.createIfMissing(destPath);
            File destFile = destPath.toFile();

            String imgExtension = getImageExtension(src.getName());

            ImageIO.write(srcImg, imgExtension, destFile);
            destFiles.add(destFile);
        }
        return destFiles;
    }

    private static String getImageExtension(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[splitFileName.length -1];
    }
}
