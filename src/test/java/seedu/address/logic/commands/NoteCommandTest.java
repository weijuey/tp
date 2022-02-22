package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class NoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private String VALID_NOTE = "Bestie";

    @Test
    void execute_addNoteUnfilteredList_success() {
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(VALID_NOTE));

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withNote(VALID_NOTE).build();
        AddressBook newAddressBook = getTypicalAddressBook();
        newAddressBook.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(NoteCommand.MESSAGE_UPDATE_NOTE_SUCCESS, editedPerson);
        Model expectedModel = new ModelManager(newAddressBook, new UserPrefs());

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }
}