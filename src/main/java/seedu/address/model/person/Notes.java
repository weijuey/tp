package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class Notes {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";

    private static final char WHITESPACE = ' ';

    public final List<String> value;

    /**
     * Constructs a new {@code Notes}.
     */
    private Notes() {
        value = new ArrayList<>();
    }

    public static boolean isValidNote(String noteToAdd) {
        boolean isAllWhiteSpace = true;
        int i = 0;
        while (isAllWhiteSpace && i < noteToAdd.length()) {
            isAllWhiteSpace = noteToAdd.charAt(i) == WHITESPACE;
            i++;
        }
        return !isAllWhiteSpace;
    }

    /**
     * Create a fresh Notes for when a new contact is
     * created, or when notes for a contact are cleared.
     * @return Notes object with empty list
     */
    public static Notes getNewNotes() {
        return new Notes();
    }

    public static Notes loadNotesFromList(List<String> notes) {
        Notes newNotes = getNewNotes();
        newNotes.value.addAll(notes);
        return newNotes;
    }

    /**
     * Appends the new note to this notes list.
     * @param newNote new note to be added
     */
    public void updateNotes(String newNote) {
        this.value.add(newNote);
    }

    public String listFormat() {
        if (value.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= value.size(); i++) {
            result.append(i).append(". ").append(value.get(i - 1)).append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && value.equals(((Notes) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
