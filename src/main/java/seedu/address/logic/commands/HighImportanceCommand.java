package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class HighImportanceCommand extends Command {
    public static final String COMMAND_WORD = "impt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a high importance tag to the person identified by the index number "
            + "used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_CHANGE_HIGH_IMPORTANCE_SUCCESS = "Changed Person's Importance Status: %1$s";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to favourite.
     */
    public HighImportanceCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    /**
     * Executes the HighImportance command based on the {@code index} given.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the result of the command.
     * @throws CommandException if the index given is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person highImportancePerson = lastShownList.get(index.getZeroBased());
        // Checks if person is already of high importance
        HighImportance highImportanceStatus =
                highImportancePerson.getHighImportanceStatus().equals(HighImportance.HIGH_IMPORTANCE)
                ? HighImportance.NOT_HIGH_IMPORTANCE
                : HighImportance.HIGH_IMPORTANCE;
        Person editedPerson = createHighImportancePerson(highImportancePerson, highImportanceStatus);

        model.setPerson(highImportancePerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_CHANGE_HIGH_IMPORTANCE_SUCCESS, editedPerson));
    }

    private static Person createHighImportancePerson(Person highImportancePerson,
                                                     HighImportance newHighImportanceStatus) {
        assert highImportancePerson != null;
        assert newHighImportanceStatus != null;

        Name name = highImportancePerson.getName();
        Phone phone = highImportancePerson.getPhone();
        Email email = highImportancePerson.getEmail();
        Address address = highImportancePerson.getAddress();
        DeadlineList deadlines = highImportancePerson.getDeadlines();
        Notes notes = highImportancePerson.getNotes();
        Favourite favouriteStatus = highImportancePerson.getFavouriteStatus();
        Set<Tag> tags = highImportancePerson.getTags();

        return new Person(name, phone, email, address, deadlines,
                notes, tags, favouriteStatus, newHighImportanceStatus);

    }
}
