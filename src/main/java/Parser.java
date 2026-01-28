import java.time.format.DateTimeParseException;

/**
 * Parses user input into commands.
 */
public class Parser {

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

        if (commandWord.equals("bye")) {
            return new ExitCommand();
        } else if (commandWord.equals("list")) {
            return new ListCommand();
        } else if (commandWord.equals("mark")) {
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
        } else if (commandWord.equals("unmark")) {
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
        } else if (commandWord.equals("delete")) {
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
        } else if (commandWord.equals("todo")) {
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                return new AddCommand(new Todo(parts[1]));
            } else {
                throw new AkException("The description of a todo cannot be empty.");
            }
        } else if (commandWord.equals("deadline")) {
            if (parts.length > 1) {
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length == 2 && !deadlineParts[0].trim().isEmpty()
                        && !deadlineParts[1].trim().isEmpty()) {
                    try {
                        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
                    } catch (DateTimeParseException e) {
                        throw new AkException(
                                "Invalid date/time format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-01 1800).");
                    }
                } else {
                    throw new AkException("Usage: deadline <description> /by <yyyy-MM-dd HHmm>");
                }
            } else {
                throw new AkException("The description of a deadline cannot be empty.");
            }
        } else if (commandWord.equals("event")) {
            if (parts.length > 1) {
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length == 2 && !eventParts[0].trim().isEmpty()) {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length == 2 && !timeParts[0].trim().isEmpty() && !timeParts[1].trim().isEmpty()) {
                        try {
                            return new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]));
                        } catch (DateTimeParseException e) {
                            throw new AkException(
                                    "Invalid date/time format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-01 1800).");
                        }
                    } else {
                        throw new AkException(
                                "Usage: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                    }
                } else {
                    throw new AkException("Usage: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                }
            } else {
                throw new AkException("The description of an event cannot be empty.");
            }
        } else {
            throw new AkException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
