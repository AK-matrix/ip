import java.util.Scanner;
import java.util.ArrayList;

/**
 * AK is a chatbot that allows for basic interaction with the user.
 * It currently supports greeting, adding tasks (todo, deadline, event),
 * listing tasks, marking tasks as done/not done, deleting tasks, and exiting.
 */
public class AK {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Main entry point of the application.
     * Initializes the chatbot and handles the user interaction loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    break;
                }
                processCommand(input);
            } catch (AkException e) {
                printOutput("OOPS!!! " + e.getMessage());
            }
        }

        exit();
        scanner.close();
    }

    /**
     * Processes the user command and executes the corresponding action.
     *
     * @param input The full user input string.
     * @throws AkException If the command is invalid or arguments are missing.
     */
    public static void processCommand(String input) throws AkException {
        String[] parts = input.split(" ", 2); // Split command and arguments
        String command = parts[0];

        if (command.equals("list")) {
            listTasks();
            return;
        }

        if (command.equals("mark")) {
            if (parts.length > 1) {
                markTask(parts[1]);
            } else {
                throw new AkException("Please specify the task number to mark.");
            }
            return;
        }

        if (command.equals("unmark")) {
            if (parts.length > 1) {
                unmarkTask(parts[1]);
            } else {
                throw new AkException("Please specify the task number to unmark.");
            }
            return;
        }

        if (command.equals("delete")) {
            if (parts.length > 1) {
                deleteTask(parts[1]);
            } else {
                throw new AkException("Please specify the task number to delete.");
            }
            return;
        }

        if (command.equals("todo")) {
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                addTask(new Todo(parts[1]));
            } else {
                throw new AkException("The description of a todo cannot be empty.");
            }
            return;
        }

        if (command.equals("deadline")) {
            if (parts.length > 1) {
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length == 2 && !deadlineParts[0].trim().isEmpty()
                        && !deadlineParts[1].trim().isEmpty()) {
                    addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                } else {
                    throw new AkException("Usage: deadline <description> /by <date/time>");
                }
            } else {
                throw new AkException("The description of a deadline cannot be empty.");
            }
            return;
        }

        if (command.equals("event")) {
            if (parts.length > 1) {
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length == 2 && !eventParts[0].trim().isEmpty()) {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts.length == 2 && !timeParts[0].trim().isEmpty() && !timeParts[1].trim().isEmpty()) {
                        addTask(new Event(eventParts[0], timeParts[0], timeParts[1]));
                    } else {
                        throw new AkException("Usage: event <description> /from <start> /to <end>");
                    }
                } else {
                    throw new AkException("Usage: event <description> /from <start> /to <end>");
                }
            } else {
                throw new AkException("The description of an event cannot be empty.");
            }
            return;
        }

        throw new AkException("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the welcome message to the console.
     */
    public static void greet() {
        printOutput("Hello! I'm AK\nWhat can I do for you?");
    }

    /**
     * Prints the exit message to the console.
     */
    public static void exit() {
        printOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Adds a task to the list and confirms the addition to the user.
     *
     * @param task The task object to add.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        printOutput(
                "Got it. I've added this task:\n  " + task + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param info The 1-based index of the task to delete.
     * @throws AkException If the task number is invalid.
     */
    public static void deleteTask(String info) throws AkException {
        try {
            int index = Integer.parseInt(info) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                printOutput("Noted. I've removed this task:\n  " + removedTask + "\n Now you have " + tasks.size()
                        + " tasks in the list.");
            } else {
                throw new AkException("Task number is out of range.");
            }
        } catch (NumberFormatException e) {
            throw new AkException("Please enter a valid task number.");
        }
    }

    /**
     * Lists all tasks currently in the list.
     */
    public static void listTasks() {
        StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(i + 1).append(".").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                listOutput.append("\n");
            }
        }
        printOutput(listOutput.toString());
    }

    /**
     * Marks a task as done based on the provided index.
     *
     * @param info The 1-based index of the task to mark.
     * @throws AkException If the task number is invalid.
     */
    public static void markTask(String info) throws AkException {
        try {
            int index = Integer.parseInt(info) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                printOutput("Nice! I've marked this task as done:\n  " + tasks.get(index));
            } else {
                throw new AkException("Task number is out of range.");
            }
        } catch (NumberFormatException e) {
            throw new AkException("Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as not done based on the provided index.
     *
     * @param info The 1-based index of the task to unmark.
     * @throws AkException If the task number is invalid.
     */
    public static void unmarkTask(String info) throws AkException {
        try {
            int index = Integer.parseInt(info) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsNotDone();
                printOutput("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
            } else {
                throw new AkException("Task number is out of range.");
            }
        } catch (NumberFormatException e) {
            throw new AkException("Please enter a valid task number.");
        }
    }

    /**
     * Prints a message formatted with horizontal lines and indentation.
     *
     * @param message The message content to be printed.
     */
    public static void printOutput(String message) {
        System.out.println("    " + HORIZONTAL_LINE);
        // Handle multi-line messages for consistent indentation
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println("     " + line);
        }
        System.out.println("    " + HORIZONTAL_LINE);
        System.out.println();
    }
}
