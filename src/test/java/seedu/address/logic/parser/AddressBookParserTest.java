package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_FRIENDS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFavouritesCommand;
import seedu.address.logic.commands.ListImportantCommand;
import seedu.address.logic.commands.UnassignTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_findTag() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTagCommand command = (FindTagCommand) parser.parseCommand(
                FindTagCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTagCommand(keywords), command);
    }

    @Test
    public void parseCommand_tag() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        CreateTagCommand command = (CreateTagCommand) parser.parseCommand(
                CreateTagCommand.COMMAND_WORD + " " + tagName);
        assertEquals(new CreateTagCommand(tagName), command);
    }

    @Test
    public void parseCommand_assign() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        AssignTagCommand command = (AssignTagCommand) parser.parseCommand(
                AssignTagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + tagName);
        assertEquals(new AssignTagCommand(INDEX_FIRST_PERSON, tagName), command);
    }

    @Test
    public void parseCommand_unassign() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        UnassignTagCommand command = (UnassignTagCommand) parser.parseCommand(
                UnassignTagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + tagName);
        assertEquals(new UnassignTagCommand(INDEX_FIRST_PERSON, tagName), command);
    }

    @Test
    public void parseCommand_deltag() throws Exception {
        List<String> keywords = Arrays.asList(VALID_TAGNAME_FRIENDS);
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(
                DeleteTagCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new DeleteTagCommand(keywords), command);
    }

    @Test
    public void parseCommand_fav() throws Exception {
        FavouriteCommand command = (FavouriteCommand) parser.parseCommand(
                FavouriteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new FavouriteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_favourites() throws Exception {
        ListFavouritesCommand command = (ListFavouritesCommand) parser.parseCommand(
                ListFavouritesCommand.COMMAND_WORD);
        assertEquals(new ListFavouritesCommand(), command);
        assertTrue(parser.parseCommand(
                ListFavouritesCommand.COMMAND_WORD + " 3") instanceof ListFavouritesCommand);
    }

    @Test
    public void parseCommand_impts() throws Exception {
        ListImportantCommand command = (ListImportantCommand) parser.parseCommand(
                ListImportantCommand.COMMAND_WORD);
        assertEquals(new ListImportantCommand(), command);
        assertTrue(parser.parseCommand(
                ListImportantCommand.COMMAND_WORD + " 3") instanceof ListImportantCommand);
    }

    @Test
    public void parseCommand_deadline() throws Exception {
        List<Deadline> deadlines = Arrays.asList(new Deadline("description", "11/11/2022"));
        DeadlineList deadlineList = new DeadlineList(deadlines);
        DeadlineCommand command = (DeadlineCommand) parser.parseCommand(
                DeadlineCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " d/description 11/11/2022");
        assertEquals(new DeadlineCommand(INDEX_FIRST_PERSON, deadlineList), command);
    }
}
