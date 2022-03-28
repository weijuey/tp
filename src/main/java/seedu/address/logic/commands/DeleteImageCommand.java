package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.image.util.ImageUtil;
import seedu.address.model.person.Person;

public class DeleteImageCommand extends Command {
    public static final String COMMAND_WORD = "delimg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the image identified by the index number used in the displayed images list of "
            + "the person identified by the index number used in the displayed person list.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) i/IMAGE_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 i/2";

    public static final String MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX = "The image index provided is invalid";
    public static final String MESSAGE_DELETE_IMAGE_SUCCESSFUL = "Image %d has been deleted for person %s";

    private final Index personIndex;
    private final Index imageIndex;

    /**
     * Constructs a command to delete an image.
     *
     * @param personIndex of the person to delete.
     * @param imageIndex of the image to delete, relative to the person to delete.
     */
    public DeleteImageCommand(Index personIndex, Index imageIndex) {
        this.personIndex = personIndex;
        this.imageIndex = imageIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        ImageDetailsList images = personToEdit.getImageDetailsList();

        if (imageIndex.getZeroBased() >= images.size()) {
            throw new CommandException(MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX);
        }

        ImageDetails imageToDelete = images.get(imageIndex.getZeroBased());
        ImageUtil.removeFile(imageToDelete);
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(images);
        personToEdit.setImageDetailsList(sanitizedList);

        return new CommandResult(
                String.format(MESSAGE_DELETE_IMAGE_SUCCESSFUL, imageIndex.getOneBased(), personToEdit));
    }
}
