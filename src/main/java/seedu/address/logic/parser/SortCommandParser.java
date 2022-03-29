package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CRITERIA;

import java.util.Arrays;

import seedu.address.logic.commands.SortsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new SortsCommand object
 */
public class SortCommandParser implements Parser<SortsCommand> {
    public static final String[] MODEL_NAMES = {
        Name.MODEL_NAME,
        Phone.MODEL_NAME,
        Email.MODEL_NAME,
        Address.MODEL_NAME,
        DeadlineList.MODEL_NAME
    };

    /**
     * Parses the given {@code String} of arguments in the context of the SortsCommand
     * and returns a SortsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortsCommand.MESSAGE_USAGE));
        }

        if (!Arrays.asList(MODEL_NAMES).contains(trimmedArgs)) {
            throw new ParseException(String.format(MESSAGE_INVALID_CRITERIA, trimmedArgs));
        }

        return new SortsCommand(trimmedArgs);
    }

}
