package seedu.address.model.image.exceptions;

/**
 * Signals that the operation will result in duplicate Images (Images are considered duplicates if they have the same
 * name (case-insensitive)).
 */
public class DuplicateImageDetailsException extends RuntimeException {
        public DuplicateImageDetailsException() {
            super("Operation would result in duplicate images");
        }
}
