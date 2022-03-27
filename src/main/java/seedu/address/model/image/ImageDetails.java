package seedu.address.model.image;

import javafx.stage.FileChooser;
import seedu.address.model.image.util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ImageDetails {
    public static final Path CONTACT_IMAGES_PATH = Path.of("data", "images");
    public final File imageFile;

    private ImageDetails(File imageFile) {
        this.imageFile = imageFile;
    }

    public static List<ImageDetails> openImageChooser() throws IOException {
        FileChooser fileChooser = ImageUtil.openImageFileChooser();

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles == null) {
            return new ArrayList<>();
        }

        List<File> uploadedImages = ImageUtil.copyTo(selectedFiles, CONTACT_IMAGES_PATH);

        return uploadedImages.stream()
                .map(ImageDetails::new)
                .collect(Collectors.toList());
    }


    /**
     * Returns true if both images have the same name.
     * This defines a weaker notion of equality between two images.
     */
    public boolean isSameImageDetails(ImageDetails otherImage) {
        if (otherImage == this) {
            return true;
        }
        return otherImage != null
                && otherImage.getName().equalsIgnoreCase(getName());
    }

    public String getName() {
        return this.imageFile.getName();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImageDetails // instanceof handles nulls
                && getName().equals(((ImageDetails) other).getName())); // state check
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getName() + ']';
    }

}
