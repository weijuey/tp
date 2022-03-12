package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Person's deadline in the address book.
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS = "Deadlines can only take dd/mm/yyyy";

    public final String value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param dateAsString a valid date.
     */
    public Deadline(String dateAsString) {
        requireNonNull(dateAsString);
        checkArgument(isValidDeadline(dateAsString));
        if (dateAsString.equals("*No deadline specified*")) {
            new Deadline();
        }
        value = dateAsString;
    }

    public Deadline() {
        value = "*No deadline specified*";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Check if given string is a valid date.
     *
     * @param dateAsString the given string.
     * @return true if given string is a valid date.
     */
    public static boolean isValidDeadline(String dateAsString) {
        if (dateAsString.equals("*No deadline specified*")) {
            return true;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;

        try {
            date = formatter.parse(dateAsString);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }
}
