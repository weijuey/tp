package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseDetailedViewExecutableSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeadlineCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE);

    private DeadlineCommandParser parser = new DeadlineCommandParser();

    @Test
    public void parse_validFieldSpecified_success() throws ParseException {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_AMY;
        List<String> deadlines = new ArrayList<>();
        deadlines.add(VALID_DEADLINE_AMY);
        DeadlineCommand expectedCommand = new DeadlineCommand(targetIndex, ParserUtil.parseDeadlines(deadlines));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validFieldSpecifiedInDetailedViewMode_success() throws ParseException {
        String userInput = DEADLINE_DESC_AMY;
        List<String> deadlines = new ArrayList<>();
        deadlines.add(VALID_DEADLINE_AMY);
        DeadlineCommand expectedCommand = new DeadlineCommand(ParserUtil.parseDeadlines(deadlines));
        assertParseDetailedViewExecutableSuccess(parser, userInput, expectedCommand);
    }
}
