package ak.ui;

import java.util.Scanner;

/**
 * Handles interaction with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of command input from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        printOutput("Hello! I'm AK\nWhat can I do for you?");
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        printOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println("    " + HORIZONTAL_LINE);
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        printOutput("OOPS!!! " + message);
    }

    /**
     * Prints a generic message to the user wrapped in horizontal lines.
     *
     * @param message The message to print.
     */
    public void printOutput(String message) {
        showLine();
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println("     " + line);
        }
        showLine();
        System.out.println();
    }

    /**
     * Prints the list of tasks found by a search.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFoundTasks(java.util.ArrayList<ak.task.Task> tasks) {
        showLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }
}
