package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddImageCommand extends Command {

    public static final String COMMAND_WORD = "addimg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an image to the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String ADD_IMAGE_SUCCESS = "Added Image to Person: %1$s";

    private final Index targetIndex;

    public AddImageCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(ADD_IMAGE_SUCCESS, targetIndex));
    }
}
