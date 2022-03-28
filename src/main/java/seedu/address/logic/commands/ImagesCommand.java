package seedu.address.logic.commands;

import seedu.address.MainApp;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.image.util.ImageUtil;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class ImagesCommand extends Command {

    public static final String COMMAND_WORD = "images";
    public static final Logger logger = Logger.getLogger(String.valueOf(MainApp.class));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the images of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_IMAGES_SUCCESS = "Images for Person: %1$s";

    private final Index index;

    public ImagesCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(index.getZeroBased());
        logger.info(String.format("Sanitizing images of person at index %d", index.getZeroBased()));

        ImageDetailsList originalList = targetPerson.getImageDetailsList();
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(originalList);
        targetPerson.setImageDetailsList(sanitizedList);
        logger.info(String.format("Result of sanitization: %d -> %d", originalList.size(), sanitizedList.size()));

        model.updateImagesToView(sanitizedList);

        String result = String.format(MESSAGE_IMAGES_SUCCESS, targetPerson + "\n") + sanitizedList;
        return new CommandResult(result, CommandResult.SpecialCommandResult.VIEW_IMAGES);
    }
}
