package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";

    /*
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    // TODO: Consider if there is a need to check for valid notes
    /*
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }
     */

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && value.equals(((Note) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
