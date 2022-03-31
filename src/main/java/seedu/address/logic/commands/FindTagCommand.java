package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all contacts in address book whose tag contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends colleague";

    public static final String TAG_NOT_EXIST = "One or more tags do not exist";

    private final TagContainsKeywordsPredicate predicate;

    public FindTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearDetailedContactView();
        model.clearActivatedTagList();
        model.updateFilteredPersonList(predicate);
        List<String> matchedKeywords = predicate.getMatchedKeyowrds();

        boolean hasAllTag = true;
        for (String keyword : matchedKeywords) {
            hasAllTag = hasAllTag && model.hasTag(new Tag(keyword));
        }

        if (!hasAllTag) {
            throw new CommandException(TAG_NOT_EXIST);
        }

        for (String matched : matchedKeywords) {
            Tag currTag = new Tag(matched);
            model.addActivatedTag(currTag);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
