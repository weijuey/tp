package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTagNames.VALID_TAGNAME_COLLEAGUES;
import static seedu.address.testutil.TypicalTagNames.VALID_TAGNAME_FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CreateTagCommand}.
 */
public class CreateTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    
    @Test
    public void execute_validTagName_success() {
        CreateTagCommand createTagCommand = new CreateTagCommand(VALID_TAGNAME_FRIENDS);
        String expectedMessage = String.format(CreateTagCommand.MESSAGE_CREATE_TAG_SUCCESS, VALID_TAGNAME_FRIENDS);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTag(new Tag(VALID_TAGNAME_FRIENDS));
        assertCommandSuccess(createTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        CreateTagCommand createTagFirstCommand = new CreateTagCommand(VALID_TAGNAME_FRIENDS);
        CreateTagCommand createTagSecondCommand = new CreateTagCommand(VALID_TAGNAME_COLLEAGUES);
        // same object -> returns true
        assertTrue(createTagFirstCommand.equals(createTagFirstCommand));

        // same values -> returns true
        CreateTagCommand createTagFirstCommandCopy = new CreateTagCommand(VALID_TAGNAME_FRIENDS);
        assertTrue(createTagFirstCommand.equals(createTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(createTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(createTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(createTagFirstCommand.equals(createTagSecondCommand));
    }

}