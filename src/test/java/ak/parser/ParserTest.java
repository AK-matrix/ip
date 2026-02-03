package ak.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ak.command.AddCommand;
import ak.command.Command;
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
