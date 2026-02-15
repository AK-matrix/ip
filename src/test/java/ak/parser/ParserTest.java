package ak.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ak.command.AddCommand;
import ak.command.AddContactCommand;
import ak.command.Command;
import ak.command.DeleteCommand;
import ak.command.DeleteContactCommand;
import ak.command.EditContactCommand;
import ak.command.ExitCommand;
import ak.command.FindCommand;
import ak.command.ListCommand;
import ak.command.ListContactCommand;
import ak.command.MarkCommand;
import ak.command.UnmarkCommand;
import ak.exception.AkException;

public class ParserTest {

    @Test
    public void parse_todoCommand_success() throws AkException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineCommand_success() throws AkException {
        Command command = Parser.parse("deadline return book /by 2022-12-12 1800");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_markCommand_success() throws AkException {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommand_success() throws AkException {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_deleteCommand_success() throws AkException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_findCommand_success() throws AkException {
        assertTrue(Parser.parse("find book") instanceof FindCommand);
    }

    @Test
    public void parse_listCommand_success() throws AkException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_exitCommand_success() throws AkException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_contactCommands_success() throws AkException {
        assertTrue(Parser.parse("add contact n/Alice p/123") instanceof AddContactCommand);
        assertTrue(Parser.parse("delete contact 1") instanceof DeleteContactCommand);
        assertTrue(Parser.parse("edit contact 1 n/Bob") instanceof EditContactCommand);
        assertTrue(Parser.parse("contact list") instanceof ListContactCommand);
    }

    @Test
    public void parse_missingArguments_throwsException() {
        assertThrows(AkException.class, () -> Parser.parse("mark"));
        assertThrows(AkException.class, () -> Parser.parse("delete"));
        assertThrows(AkException.class, () -> Parser.parse("find"));
        assertThrows(AkException.class, () -> Parser.parse("todo"));
        assertThrows(AkException.class, () -> Parser.parse("deadline desc"));
        assertThrows(AkException.class, () -> Parser.parse("event desc /from now"));
    }

    @Test
    public void parse_emptyCommand_exception() {
        try {
            Parser.parse("");
            fail("Expected AkException was not thrown");
        } catch (AkException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_exception() {
        try {
            Parser.parse("blah");
            fail("Expected AkException was not thrown");
        } catch (AkException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
