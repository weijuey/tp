package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Favourite {

    private final Boolean isFavourite;
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param isFavourite The favourite status.
     */
    public Favourite(Boolean isFavourite) {
        requireNonNull(isFavourite);
        this.isFavourite = isFavourite;
        value = isFavourite.toString();
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
