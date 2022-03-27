package seedu.address.model.image;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.image.exceptions.DuplicateImageDetailsException;
import seedu.address.model.image.exceptions.ImageDetailsNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class UniqueImageDetailsList implements Iterable<ImageDetails> {

    private final ObservableList<ImageDetails> internalList = FXCollections.observableArrayList();
    private final ObservableList<ImageDetails> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(ImageDetails toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameImageDetails);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(ImageDetails toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateImageDetailsException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent image from the list.
     * The image must exist in the list.
     */
    public void remove(ImageDetails toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ImageDetailsNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ImageDetails> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ImageDetails> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueImageDetailsList // instanceof handles nulls
                && internalList.equals(((UniqueImageDetailsList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code imageDetails} contains only unique image details.
     */
    private boolean imageDetailsAreUnique(List<ImageDetails> images) {
        for (int i = 0; i < images.size() - 1; i++) {
            for (int j = i + 1; j < images.size(); j++) {
                if (images.get(i).isSameImageDetails(images.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
