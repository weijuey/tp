package seedu.address.model.image;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailsList {
    private final List<ImageDetails> images;

    public ImageDetailsList() {
        this.images = new ArrayList<>();
    }

    public ImageDetailsList(List<ImageDetails> images) {
        this.images = images;
    }

    public ImageDetailsList appendImageDetails(List<ImageDetails> appendedImages) {
        List<ImageDetails> newImages = new ArrayList<>();
        newImages.addAll(images);
        newImages.addAll(appendedImages);
        return new ImageDetailsList(newImages);
    }

    public boolean isEmpty() {
        return this.images.isEmpty();
    }
}
