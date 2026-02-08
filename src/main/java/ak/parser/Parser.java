package ak.parser;

import java.time.format.DateTimeParseException;

import ak.command.AddCommand;
import ak.command.Command;
import ak.command.DeleteCommand;
import ak.command.ExitCommand;
import ak.command.FindCommand;
import ak.command.ListCommand;
import ak.command.MarkCommand;
import ak.command.UnmarkCommand;
import ak.exception.AkException;
import ak.task.Deadline;
import ak.task.Event;
import ak.task.Todo;

/**
 * Parses user input into commands.
 */
public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String ERROR_INVALID_DATE_FORMAT = "Invalid date/time format. "
            + "Please use yyyy-MM-dd HHmm (e.g., 2019-12-01 1800).";
    private static final String ERROR_EVENT_USAGE = "Usage: event <description> /from "
            + "<yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>";
    private static final String ERROR_DEADLINE_USAGE = "Usage: deadline <description> /by <yyyy-MM-dd HHmm>";

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full user input string.
     * @return The parsed Command object.
     * @throws AkException If the command is invalid or arguments are missing.
     */
    public static Command parse(String fullCommand) throws AkException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        if (commandWord.equals(COMMAND_BYE)) {
            return new ExitCommand();
        } else if (commandWord.equals(COMMAND_LIST)) {
            return new ListCommand();
        } else if (commandWord.equals(COMMAND_MARK)) {
            if (parts.length > 1) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new AkException("Please enter a valid task number.");
                }
            } else {
                throw new AkException("Please specify the task number to mark.");
            }
        } else if (commandWord.equals(COMMAND_UNMARK)) {
            if (parts.length > 1) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new AkException("Please enter a valid task number.");
                }
            } else {
                throw new AkException("Please specify the task number to unmark.");
            }
        } else if (commandWord.equals(COMMAND_DELETE)) {
            if (parts.length > 1) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new AkException("Please enter a valid task number.");
                }
            } else {
                throw new AkException("Please specify the task number to delete.");
            }
        } else if (commandWord.equals(COMMAND_FIND)) {
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                return new FindCommand(parts[1].trim());
            } else {
                throw new AkException("The keyword to find cannot be empty.");
            }
        } else if (commandWord.equals(COMMAND_TODO)) {
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                return new AddCommand(new Todo(parts[1]));
            } else {
                throw new AkException("The description of a todo cannot be empty.");
            }
        } else if (commandWord.equals(COMMAND_DEADLINE)) {
            if (parts.length > 1) {
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length == 2 && !deadlineParts[0].trim().isEmpty()
                        && !deadlineParts[1].trim().isEmpty()) {
                    try {
                        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
                    } catch (DateTimeParseException e) {
                        throw new AkException(ERROR_INVALID_DATE_FORMAT);
                    }
                } else {
                    throw new AkException(ERROR_DEADLINE_USAGE);
                }
            } else {
                throw new AkException("The description of a deadline cannot be empty.");
            }
        } else if (commandWord.equals(COMMAND_EVENT)) {
            if (parts.length > 1) {
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length == 2 && !eventParts[0].trim().isEmpty()) {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length == 2 && !timeParts[0].trim().isEmpty() && !timeParts[1].trim().isEmpty()) {
                        try {
                            return new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]));
                        } catch (DateTimeParseException e) {
                            throw new AkException(ERROR_INVALID_DATE_FORMAT);
                        }
                    } else {
                        throw new AkException(ERROR_EVENT_USAGE);
                    }
                } else {
                    throw new AkException(ERROR_EVENT_USAGE);
                }
            } else {
                throw new AkException("The description of an event cannot be empty.");
            }
        } else {
            throw new AkException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
