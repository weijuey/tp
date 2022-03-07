package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class Notes {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";

    private final List<String> notes;

    /**
     * Constructs a new {@code Notes}.
     */
    private Notes() {
        notes = new ArrayList<>();
    }

    private Notes(Notes old, String newNote) {
        notes = old.notes;
        notes.add(newNote);
    }

    // TODO: Consider if there is a need to check for valid notes
    /*
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    */

    /**
     * Create a fresh Notes for when a new contact is
     * created, or when notes for a contact are cleared.
     * @return Notes object with empty list
     */
    public static Notes getNewNotes() {
        return new Notes();
    }

    /**
     * Create a new Notes object with the old notes list
     * and the new note appended to it.
     * @param newNote new note to be added
     * @return new Notes object with the updated list
     */
    public Notes updateNotes(String newNote) {
        return new Notes(this, newNote);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= notes.size(); i++) {
            result.append(i).append(". ").append(notes.get(i - 1)).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && notes.equals(((Notes) other).notes)); // state check
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }

}
