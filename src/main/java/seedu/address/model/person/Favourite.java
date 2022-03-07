package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Favourite {
    public static final Favourite IS_FAVOURITE = new Favourite(true);
    public static final Favourite NOT_FAVOURITE = new Favourite(false);
    public static final String MESSAGE_CONSTRAINTS =
            "Favourite should be either true or false, and should not be blank.";

    public final String value;

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Constructs a {@code Phone}.
     *
     * @param isFavourite The favourite status.
     */
    private Favourite(Boolean isFavourite) {
        requireNonNull(isFavourite);
        value = isFavourite.toString();
    }

    public static Favourite valueOf(String isFavourite) {
        return "true".equals(isFavourite)
                ? IS_FAVOURITE
                : NOT_FAVOURITE;
    }

    /**
     * Returns true if a given string is a valid favourite status.
     */
    public static boolean isValidFavourite(String test) {
        return test.matches(VALIDATION_REGEX)
                && ("true".equals(test) || "false".equals(test));
    }


    public boolean isFavourite() {
        return this.equals(IS_FAVOURITE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Favourite // instanceof handles nulls
                && value.equals(((Favourite) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
