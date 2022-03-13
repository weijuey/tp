package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import java.util.List;

class NoteCommandTest {
    private static final String VALID_NOTE = "Bestie";
    private static final String INVALID_NOTE = "    ";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addValidNote_success() {
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, VALID_NOTE);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withNotes(List.of(VALID_NOTE)).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(NoteCommand.MESSAGE_UPDATE_NOTE_SUCCESS, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addInvalidNote_noChange() {
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, INVALID_NOTE);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(NoteCommand.MESSAGE_NO_UPDATE_TO_NOTES, personToEdit);

        assertCommandSuccess(noteCommand, model, expectedMessage, model);
    }
}
