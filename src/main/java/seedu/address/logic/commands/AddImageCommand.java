package seedu.address.logic.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.stage.FileChooser;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.image.util.ImageUtil;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.image.ImageDetails.CONTACT_IMAGES_PATH;

public class AddImageCommand extends Command {

    private static final Logger logger = Logger.getLogger(String.valueOf(AddImageCommand.class));

    public static final String COMMAND_WORD = "addimg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an image to the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String ADD_IMAGE_SUCCESS = "Added %d image(s) to Person: %s";
    public static final String ADD_IMAGE_NONE_SELECTED = "No images were selected to be added";
    public static final String ADD_IMAGE_FAIL = "Failed to add image(s)";
    public static final String DUPLICATE_IMAGES
            = "An image with the name: \"%s\" already exists";

    private final Index targetIndex;

    public AddImageCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<File> images = openImageChooser();

        if (images.isEmpty()) {
            return new CommandResult(ADD_IMAGE_NONE_SELECTED);
        }

        StringBuilder resultStringBuilder = new StringBuilder();
        List<ImageDetails> imagesToAdd = new ArrayList<>();
        for (File imgFile : images) {
            Path destPath = Path.of(CONTACT_IMAGES_PATH.toString(), imgFile.getName());
            if (ImageUtil.fileExists(imgFile, CONTACT_IMAGES_PATH)) {
                resultStringBuilder
                        .append(String.format(DUPLICATE_IMAGES, imgFile.getName()))
                        .append("\n");
                continue;
            }
            ImageDetails copiedImage;
            try {
                // Uploads the file to the designated path @ destPath.
                // TODO: let storage/addressbook handle this, so that users can set their "contactImagesPath" pref.
                 copiedImage = ImageUtil.copyTo(imgFile, destPath);
            } catch (IOException ioe) {
                throw new CommandException(ADD_IMAGE_FAIL);
            }

            imagesToAdd.add(copiedImage);
        }
        resultStringBuilder.append(String.format(ADD_IMAGE_SUCCESS, imagesToAdd.size(), targetIndex));

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = addImages(personToEdit, imagesToAdd);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(resultStringBuilder.toString());
    }

    private static Person addImages(Person personToEdit, List<ImageDetails> images) {
        ImageDetailsList oldImages = personToEdit.getImageDetailsList();
        ImageDetailsList newImages = oldImages.appendImageDetails(images);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDeadlines(), personToEdit.getNotes(),
                personToEdit.getTags(), personToEdit.getFavouriteStatus());
        editedPerson.setImageDetailsList(newImages);
        return editedPerson;
    }

    private List<File> openImageChooser() {
        logger.info("Launching file chooser");
        FileChooser fileChooser = ImageUtil.openImageFileChooser();

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles == null) {
            return new ArrayList<>();
        }
        return selectedFiles;
    }
}
