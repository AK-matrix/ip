import java.util.Scanner;

/**
 * AK is a chatbot that allows for basic interaction with the user.
 * It currently supports greeting, adding tasks (todo, deadline, event),
 * listing tasks, marking tasks as done/not done, and exiting.
 */
public class AK {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
            String[] parts = input.split(" ", 2); // Split command and arguments
            String command = parts[0];

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.equals("mark")) {
                if (parts.length > 1) {
                    markTask(parts[1]);
                } else {
                    printOutput("Please specify the task number to mark.");
                }
            } else if (command.equals("unmark")) {
                if (parts.length > 1) {
                    unmarkTask(parts[1]);
                } else {
                    printOutput("Please specify the task number to unmark.");
                }
            } else if (command.equals("todo")) {
                if (parts.length > 1) {
                    addTask(new Todo(parts[1]));
                } else {
                    printOutput("The description of a todo cannot be empty.");
                }
            } else if (command.equals("deadline")) {
                if (parts.length > 1) {
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    if (deadlineParts.length == 2) {
                        addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                    } else {
                        printOutput("Usage: deadline <description> /by <date/time>");
                    }
                } else {
                    printOutput("The description of a deadline cannot be empty.");
                }
            } else if (command.equals("event")) {
                if (parts.length > 1) {
                    String[] eventParts = parts[1].split(" /from ", 2);
                    if (eventParts.length == 2) {
                        String[] timeParts = eventParts[1].split(" /to ", 2);
                        if (timeParts.length == 2) {
                            addTask(new Event(eventParts[0], timeParts[0], timeParts[1]));
                        } else {
                            printOutput("Usage: event <description> /from <start> /to <end>");
                        }
                    } else {
                        printOutput("Usage: event <description> /from <start> /to <end>");
                    }
                } else {
                    printOutput("The description of an event cannot be empty.");
                }
            } else {
                printOutput("Unknown command. Please try again.");
            }
        }

        exit();
        scanner.close();
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
        tasks[taskCount] = task;
        taskCount++;
        printOutput(
                "Got it. I've added this task:\n  " + task + "\n Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Lists all tasks currently in the list.
     */
    public static void listTasks() {
        StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            listOutput.append(i + 1).append(".").append(tasks[i]);
            if (i < taskCount - 1) {
                listOutput.append("\n");
            }
        }
        printOutput(listOutput.toString());
    }

    /**
     * Marks a task as done based on the provided index.
     *
     * @param info The 1-based index of the task to mark.
     */
    public static void markTask(String info) {
        try {
            int index = Integer.parseInt(info) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsDone();
                printOutput("Nice! I've marked this task as done:\n  " + tasks[index]);
            } else {
                printOutput("Task number is out of range.");
            }
        } catch (NumberFormatException e) {
            printOutput("Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as not done based on the provided index.
     *
     * @param info The 1-based index of the task to unmark.
     */
    public static void unmarkTask(String info) {
        try {
            int index = Integer.parseInt(info) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsNotDone();
                printOutput("OK, I've marked this task as not done yet:\n  " + tasks[index]);
            } else {
                printOutput("Task number is out of range.");
            }
        } catch (NumberFormatException e) {
            printOutput("Please enter a valid task number.");
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
