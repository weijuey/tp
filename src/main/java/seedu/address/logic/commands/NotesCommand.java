package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class NotesCommand extends Command {
    public static final String COMMAND_WORD = "notes";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Prints the full list of notes of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String SUCCESSFUL_MESSAGE_FORMAT = "Notes for %s:\n%s";

    private final Index index;

    /**
     * @param index index of the contact in the filtered list to list notes
     */
    public NotesCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGetNotes = lastShownList.get(index.getZeroBased());
        return new CommandResult(generateSuccessMessage(personToGetNotes));
    }

    private static String generateSuccessMessage(Person person) {
        return String.format(SUCCESSFUL_MESSAGE_FORMAT,
                person.getName().fullName, person.getNotes().listFormat());
    }
}
