package seedu.address.logic.commands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetails;

public class AddImageCommand extends Command {

    private final Logger logger = Logger.getLogger("Add image logger");

    public static final String COMMAND_WORD = "addimg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an image to the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String ADD_IMAGE_SUCCESS = "Added %d image(s) to Person: %s";
    public static final String ADD_IMAGE_NONE_SELECTED = "No images were selected to be added";
    public static final String ADD_IMAGE_FAIL = "Failed to add image";
    public static final String DUPLICATE_IMAGES
            = "An image with the name: \"%s\" already exists";

    private final Index targetIndex;

    public AddImageCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<ImageDetails> images;
        try {
            images = ImageDetails.openImageChooser();
            logger.info("Launching file chooser");
        } catch (FileAlreadyExistsException faee) {
            logger.warning(faee.getMessage());
            throw new CommandException(String.format(DUPLICATE_IMAGES, faee.getFile()));
        } catch (IOException ie) {
            logger.warning(ie.getMessage());
            throw new CommandException(ADD_IMAGE_FAIL);
        }

        if (images.isEmpty()) {
            return new CommandResult(ADD_IMAGE_NONE_SELECTED);
        }

        return new CommandResult(String.format(ADD_IMAGE_SUCCESS, images.size(), targetIndex));
    }
}
