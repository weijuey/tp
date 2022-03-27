package seedu.address.model.image;

import java.io.File;
import java.nio.file.Path;

public class ImageDetails {
    public static final Path CONTACT_IMAGES_PATH = Path.of("data", "images");
    public final File imageFile;

    public ImageDetails(File imageFile) {
        this.imageFile = imageFile;
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
