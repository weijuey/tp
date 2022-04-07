package seedu.address.model.image;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_1;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_2;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_3;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_4;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_5;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_6;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_7;
import static seedu.address.testutil.TypicalSavedImages.getTypicalImageDetailsList;

class ImageDetailsListTest {

    @Test
    void appendImageDetails_success() {
        ImageDetailsList expectedList = new ImageDetailsList(List.of(
                TEST_IMAGE_1,
                TEST_IMAGE_2,
                TEST_IMAGE_3,
                TEST_IMAGE_4,
                TEST_IMAGE_5,
                TEST_IMAGE_6,
                TEST_IMAGE_7
        ));

        ImageDetailsList originalTestList = getTypicalImageDetailsList();
        List<ImageDetails> listToAppend = List.of( TEST_IMAGE_6, TEST_IMAGE_7);
        ImageDetailsList actualList = originalTestList.appendImageDetails(listToAppend);

        assertEquals(expectedList, actualList);

    }

    @Test
    void getImages() {
    }

    @Test
    void get() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}
