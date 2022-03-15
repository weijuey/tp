package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.Person;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Gives a deadline to person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 d/01/01/2022";

    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "Added deadline for: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index targetIndex;
    private final Deadline deadline;

    /**
     * Creates a DeadlineCommand to add to specified {@code Person}.
     * @param targetIndex the index of the person specified.
     * @param deadline the date of the deadline.
     */
    public DeadlineCommand(Index targetIndex, Deadline deadline) {
        requireNonNull(targetIndex);
        requireNonNull(deadline);
        this.targetIndex = targetIndex;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddDeadline = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = new Person(
                personToAddDeadline.getName(), personToAddDeadline.getPhone(), personToAddDeadline.getEmail(),
                personToAddDeadline.getAddress(), deadline, personToAddDeadline.getNotes(),
                personToAddDeadline.getTags(), personToAddDeadline.getFavouriteStatus());

        if (!personToAddDeadline.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToAddDeadline, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_DEADLINE_SUCCESS, personToAddDeadline));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineCommand // instanceof handles nulls
                && targetIndex.equals(((DeadlineCommand) other).targetIndex))
                && deadline.equals(((DeadlineCommand) other).deadline); // state check
    }

}
