package ak.parser;

import java.time.format.DateTimeParseException;

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
import ak.contact.Contact;
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
        assert fullCommand != null : "Command string cannot be null";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        if (isContactCommand(commandWord, parts)) {
            return prepareContactCommand(commandWord, parts);
        }

        switch (commandWord) {
            case COMMAND_BYE:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_MARK:
                return prepareMarkCommand(parts);
            case COMMAND_UNMARK:
                return prepareUnmarkCommand(parts);
            case COMMAND_DELETE:
                return prepareDeleteCommand(parts);
            case COMMAND_FIND:
                return prepareFindCommand(parts);
            case COMMAND_TODO:
            case COMMAND_DEADLINE:
            case COMMAND_EVENT:
                return prepareTaskCommand(commandWord, parts);
            default:
                throw new AkException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isContactCommand(String commandWord, String[] parts) {
        if (commandWord.equals("contact")) {
            return true;
        }
        if (parts.length > 1 && parts[1].startsWith("contact")) {
            return commandWord.equals("add") || commandWord.equals("delete") || commandWord.equals("edit");
        }
        return false;
    }

    private static Command prepareContactCommand(String commandWord, String[] parts) throws AkException {
        if (commandWord.equals("add")) {
            return parseAddContact(parts[1].substring(7).trim());
        } else if (commandWord.equals("contact") && parts.length > 1 && parts[1].trim().equals("list")) {
            return new ListContactCommand();
        } else if (commandWord.equals("delete")) {
            return parseDeleteContact(parts[1].substring(7).trim());
        } else if (commandWord.equals("edit")) {
            return parseEditContact(parts[1].substring(7).trim());
        }
        throw new AkException("Invalid contact command.");
    }

    private static Command prepareMarkCommand(String[] parts) throws AkException {
        if (parts.length > 1) {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new AkException("Please enter a valid task number.");
            }
        }
        throw new AkException("Please specify the task number to mark.");
    }

    private static Command prepareUnmarkCommand(String[] parts) throws AkException {
        if (parts.length > 1) {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new AkException("Please enter a valid task number.");
            }
        }
        throw new AkException("Please specify the task number to unmark.");
    }

    private static Command prepareDeleteCommand(String[] parts) throws AkException {
        if (parts.length > 1) {
            try {
                int index = Integer.parseInt(parts[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new AkException("Please enter a valid task number.");
            }
        }
        throw new AkException("Please specify the task number to delete.");
    }

    private static Command prepareFindCommand(String[] parts) throws AkException {
        if (parts.length > 1 && !parts[1].trim().isEmpty()) {
            return new FindCommand(parts[1].trim());
        }
        throw new AkException("The keyword to find cannot be empty.");
    }

    private static Command prepareTaskCommand(String commandWord, String[] parts) throws AkException {
        if (commandWord.equals(COMMAND_TODO)) {
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                return new AddCommand(new Todo(parts[1]));
            }
            throw new AkException("The description of a todo cannot be empty.");
        } else if (commandWord.equals(COMMAND_DEADLINE)) {
            return prepareDeadlineCommand(parts);
        } else if (commandWord.equals(COMMAND_EVENT)) {
            return prepareEventCommand(parts);
        }
        throw new AkException("Unknown task command.");
    }

    private static Command prepareDeadlineCommand(String[] parts) throws AkException {
        if (parts.length > 1) {
            String[] deadlineParts = parts[1].split(" /by ", 2);
            if (deadlineParts.length == 2 && !deadlineParts[0].trim().isEmpty() && !deadlineParts[1].trim().isEmpty()) {
                try {
                    return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
                } catch (DateTimeParseException e) {
                    throw new AkException(ERROR_INVALID_DATE_FORMAT);
                }
            }
            throw new AkException(ERROR_DEADLINE_USAGE);
        }
        throw new AkException("The description of a deadline cannot be empty.");
    }

    private static Command prepareEventCommand(String[] parts) throws AkException {
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
                }
                throw new AkException(ERROR_EVENT_USAGE);
            }
            throw new AkException(ERROR_EVENT_USAGE);
        }
        throw new AkException("The description of an event cannot be empty.");
    }

    private static Command parseAddContact(String args) throws AkException {
        if (args.isEmpty()) {
            throw new AkException("Usage: add contact n/<name> e/<email> p/<number> i/<info>");
        }
        String name = extractArgument(args, "n/");
        String email = extractArgument(args, "e/");
        String phone = extractArgument(args, "p/");
        String info = extractArgument(args, "i/");

        if (name == null || name.isEmpty()) {
            throw new AkException("Contact name (n/) is required.");
        }
        if (email == null) {
            email = "";
        }
        if (phone == null) {
            phone = "";
        }
        if (info == null) {
            info = "";
        }

        return new AddContactCommand(new Contact(name, email, phone, info));
    }

    /**
     * Parses arguments for deleting a contact.
     *
     * @param args The argument string.
     * @return The DeleteContactCommand.
     * @throws AkException If arguments are invalid.
     */
    private static Command parseDeleteContact(String args) throws AkException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            return new DeleteContactCommand(index);
        } catch (NumberFormatException e) {
            throw new AkException("Please enter a valid contact number.");
        }
    }

    /**
     * Parses arguments for editing a contact.
     *
     * @param args The argument string.
     * @return The EditContactCommand.
     * @throws AkException If arguments are invalid.
     */
    private static Command parseEditContact(String args) throws AkException {
        // Format: edit contact <index> n/name ...
        String[] parts = args.trim().split(" ", 2);
        if (parts.length < 2) {
            throw new AkException("Usage: edit contact <index> [n/<name>] [e/<email>] [p/<phone>] [i/<info>]");
        }

        int index;
        try {
            index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new AkException("Please enter a valid contact number.");
        }

        String details = parts[1];
        String name = extractArgument(details, "n/");
        String email = extractArgument(details, "e/");
        String phone = extractArgument(details, "p/");
        String info = extractArgument(details, "i/");

        if (name == null && email == null && phone == null && info == null) {
            throw new AkException("At least one field to edit must be provided.");
        }

        return new EditContactCommand(index, name, email, phone, info);
    }

    /**
     * Extracts an argument value given a prefix.
     *
     * @param text   The full text.
     * @param prefix The argument prefix (e.g., "n/").
     * @return The extracted argument value, or null if not found.
     */
    private static String extractArgument(String text, String prefix) {
        int startIndex = text.indexOf(prefix);
        if (startIndex == -1) {
            return null;
        }

        // Start reading after the prefix
        int contentStart = startIndex + prefix.length();

        // Find the start of the next argument or end of string
        // We look for other prefixes " n/", " e/", " p/", " i/" to allow spaces
        // in content
        int nextPrefixIndex = text.length();
        String[] prefixes = { " n/", " e/", " p/", " i/" };

        for (String p : prefixes) {
            int idx = text.indexOf(p, contentStart);
            // Ensure we don't pick up the same prefix if it checks itself or
            // earlier ones
            // But since we start searching from contentStart, we are safe
            // looking forward
            if (idx != -1 && idx < nextPrefixIndex) {
                nextPrefixIndex = idx;
            }
        }

        return text.substring(contentStart, nextPrefixIndex).trim();
    }
}
