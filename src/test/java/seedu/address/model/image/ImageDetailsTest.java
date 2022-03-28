package seedu.address.model.image;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class ImageDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImageDetails(null));
    }

    @Test
    void getName() {
        String fileName = "name.test";
        File file = Path.of("test", fileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(fileName, imageDetails.getName());
    }

    @Test
    void getImageFile() {
        String fileName = "name.test";
        File file = Path.of("test", fileName).toFile();
        ImageDetails imageDetails = new ImageDetails(file);
        assertEquals(file, imageDetails.getImageFile());
    }

    @Test
    void getAbsolutePath() {
        String fileName = "name.test";
        Path path = Path.of("test", fileName);

        File file = path.toFile();
        ImageDetails imageDetails = new ImageDetails(file);

        assertEquals(path.toString(), imageDetails.getPath(Paths.get("test")));
    }

    @Test
    void getJavaFXImageUrl() {
        String fileName = "name.test";

        Path path = ImageDetails.CONTACT_IMAGES_PATH.resolve(fileName);
        File file = path.toFile();

        ImageDetails imageDetails = new ImageDetails(file);
        String expected = String.format("file:%s", path);

        assertEquals(expected, imageDetails.getJavaFXImageUrl());
    }
}
