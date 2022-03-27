package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_OWESMONEY;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_TEST;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnassignTagCommand}.
 */
public class UnassignTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexCreatedTagTaggedPerson_success() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert personToAddTag == ALICE;
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Tag removingTag = VALID_TAG_FRIENDS;
        Person editedPerson = new PersonBuilder(personToAddTag).withoutTag(removingTag).build();
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_SUCCESS, editedPerson);
        expectedModel.setPerson(personToAddTag, editedPerson);

        assertCommandSuccess(unassignTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexCreatedTagUntaggedPerson_throwsCommandException() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assert personToAddTag == ALICE;
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_OWESMONEY);

        assertCommandFailure(unassignTagCommand, model, UnassignTagCommand.MESSAGE_NOT_TAGGED);
    }

    @Test
    public void execute_validIndexUncreatedTag_throwsCommandException() {
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);

        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexCreatedTag_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(outOfBoundIndex, VALID_TAGNAME_FRIENDS);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUncreatedTag_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(outOfBoundIndex, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);
        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnassignTagCommand unassignTagFirstCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);
        UnassignTagCommand unassignTagSecondCommand = new UnassignTagCommand(INDEX_SECOND_PERSON,
                VALID_TAGNAME_COLLEAGUES);

        // same object -> returns true
        assertTrue(unassignTagFirstCommand.equals(unassignTagFirstCommand));

        // same values -> returns true
        UnassignTagCommand unassignTagFirstCommandCopy = new UnassignTagCommand(INDEX_FIRST_PERSON,
                VALID_TAGNAME_FRIENDS);
        assertTrue(unassignTagFirstCommand.equals(unassignTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(unassignTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unassignTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unassignTagFirstCommand.equals(unassignTagSecondCommand));
    }

}
