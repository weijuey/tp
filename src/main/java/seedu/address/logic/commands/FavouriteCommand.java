package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "fav";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    public FavouriteCommand(Index index) {
       requireNonNull(index);

       this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from favourites");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavouriteCommand)) {
            return false;
        }

        FavouriteCommand e = (FavouriteCommand) other;
        return index.equals(e.index);
    }
}
