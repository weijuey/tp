package seedu.address.model.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class ImageDetailsTest {
    Path testImageDirectory = Paths.get("src", "test", "data", "testImages");
    String testFileName = "test_image_1.png";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImageDetails(null));
    }

    @Test
    void getName() {
        File file = testImageDirectory.resolve(testFileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(testFileName, imageDetails.getName());
    }

    @Test
    void getImageFile() {
        File file = testImageDirectory.resolve(testFileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(file, imageDetails.getImageFile());
    }

    @Test
    void getPath() {
        Path path = testImageDirectory.resolve(testFileName);
        File file = path.toFile();
        ImageDetails imageDetails = new ImageDetails(file);

        assertEquals(path.toString(), imageDetails.getPath(testImageDirectory));
    }

    @Test
    void getJavaFxImageUrl() {
        Path path = ImageDetails.CONTACT_IMAGES_PATH.resolve(testFileName);
        File file = path.toFile();

        ImageDetails imageDetails = new ImageDetails(file);
        String expected = String.format("file:%s", path);

        assertEquals(expected, imageDetails.getJavaFxImageUrl());
    }
}
