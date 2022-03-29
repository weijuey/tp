package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsAnyKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Deletes tags that contain any of the argument keywords, and unassigns the tag from any contacts.
 * Keyword matching is case insensitive.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deltag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes tags that contain any of "
            + "the specified keywords (case-insensitive), automatically unassigns the tag from any contacts "
            + "and displays a list of contacts who had the tags unassigned from them."
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends colleague";

    public static final String MESSAGE_FAIL = "Could not delete the given tags";

    private final TagContainsAnyKeywordsPredicate predicate;

    public DeleteTagCommand(TagContainsAnyKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearDetailedContactView();
        model.updateFilteredPersonList(predicate);
        ObservableList<Person> currList = model.getFilteredPersonList();
        Index firstIndex = Index.fromZeroBased(1);
        Index lastIndex = Index.fromZeroBased(currList.size());
        for (String keyword : predicate.getKeywords()) {
            for (int i = firstIndex.getZeroBased(); i <= lastIndex.getZeroBased(); i++) {
                try {
                    UnassignCommand unassignCommand = new UnassignCommand(i, keyword);
                    unassignCommand.execute();
                } catch (CommandException e) {
                    throw new CommandException(MESSAGE_FAIL);

                }
            }
            Tag toDelete = new Tag(keyword);
            model.deleteTag(toDelete);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, currList.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && predicate.equals(((DeleteTagCommand) other).predicate)); // state check
    }
}
