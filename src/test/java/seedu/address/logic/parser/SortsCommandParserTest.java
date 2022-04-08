package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortsCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class SortsCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortsCommand expectedSortCommand =
                new SortsCommand("name");
        assertParseSuccess(parser, "name", expectedSortCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n \t   \t", expectedSortCommand);
    }

    @Test
    public void criteriaListForUsageMessage_returnsString() {
        String expected = Name.MODEL_NAME + ", " + Phone.MODEL_NAME + ", " + Email.MODEL_NAME + ", "
                + Address.MODEL_NAME + ", " + DeadlineList.MODEL_NAME + ", " + Favourite.MODEL_NAME + ", "
                + HighImportance.MODEL_NAME;
        assertEquals(SortCommandParser.criteriaListForUsageMessage(), expected);
    }
}
