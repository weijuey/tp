package seedu.address.model.image;

import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageDetailsList implements Iterable<ImageDetails> {
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

    public List<ImageDetails> getImages() {
        return this.images;
    }

    public boolean isEmpty() {
        return this.images.isEmpty();
    }

    public int size() {
        return this.images.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < images.size(); i++) {
            sb.append(i+1).append(": ").append(images.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over elements of type {@code Images}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ImageDetails> iterator() {
        return this.images.iterator();
    }
}
