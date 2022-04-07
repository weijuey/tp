package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code ImageDetails} objects to be used in tests.
 */
public class TypicalSavedImages {
    public static final Path TEST_IMAGES_DIRECTORY = Paths.get("src", "test", "data", "testImages");

    // Typical files
    public static final File TEST_IMAGE_1_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_1.png").toFile();
    public static final File TEST_IMAGE_2_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_2.png").toFile();
    public static final File TEST_IMAGE_3_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_3.png").toFile();
    public static final File TEST_IMAGE_4_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_4.png").toFile();
    public static final File TEST_IMAGE_5_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_5.png").toFile();

    // Manually added files
    public static final File TEST_IMAGE_6_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_6.png").toFile();
    public static final File TEST_IMAGE_7_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_7.png").toFile();
    public static final File TEST_IMAGE_8_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_8.png").toFile();
    public static final File TEST_IMAGE_9_FILE = TEST_IMAGES_DIRECTORY.resolve("test_image_9.png").toFile();

    // Typical images
    public static final ImageDetails TEST_IMAGE_1 = new ImageDetails(TEST_IMAGE_1_FILE);
    public static final ImageDetails TEST_IMAGE_2 = new ImageDetails(TEST_IMAGE_2_FILE);
    public static final ImageDetails TEST_IMAGE_3 = new ImageDetails(TEST_IMAGE_3_FILE);
    public static final ImageDetails TEST_IMAGE_4 = new ImageDetails(TEST_IMAGE_4_FILE);
    public static final ImageDetails TEST_IMAGE_5 = new ImageDetails(TEST_IMAGE_5_FILE);

    // Manually added images
    public static final ImageDetails TEST_IMAGE_6 = new ImageDetails(TEST_IMAGE_6_FILE);
    public static final ImageDetails TEST_IMAGE_7 = new ImageDetails(TEST_IMAGE_7_FILE);
    public static final ImageDetails TEST_IMAGE_8 = new ImageDetails(TEST_IMAGE_8_FILE);
    public static final ImageDetails TEST_IMAGE_9 = new ImageDetails(TEST_IMAGE_9_FILE);

    /**
     * Returns an {@code ImageDetailsList} with all the typical images.
     */
    public static ImageDetailsList getTypicalImageDetailsList() {
        return new ImageDetailsList(getTypicalImageDetails());
    }

    public static List<ImageDetails> getTypicalImageDetails() {
        return new ArrayList<>(Arrays.asList(TEST_IMAGE_1, TEST_IMAGE_2, TEST_IMAGE_3, TEST_IMAGE_4, TEST_IMAGE_5));
    }

}
