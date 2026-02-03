package ak.ui;

import java.util.Scanner;

/**
 * Handles interaction with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner scanner;

    private StringBuilder outputBuffer = new StringBuilder();
    private boolean isGuiMode = false;

    /**
     * Constructs a Ui object and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void setGuiMode(boolean guiMode) {
        this.isGuiMode = guiMode;
    }

    public String getResponse() {
        String response = outputBuffer.toString();
        outputBuffer.setLength(0); // Clear buffer
        return response;
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
     * Prints the welcome message to the user.
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
        if (!isGuiMode) {
            System.out.println("    " + HORIZONTAL_LINE);
        }
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
    public void printOutput(String... messages) {
        if (isGuiMode) {
            for (String message : messages) {
                outputBuffer.append(message).append("\n");
            }
        } else {
            showLine();
            for (String message : messages) {
                String[] lines = message.split("\n");
                for (String line : lines) {
                    System.out.println("     " + line);
                }
            }
            showLine();
            System.out.println();
        }
    }

    /**
     * Prints the list of tasks found by a search.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFoundTasks(java.util.ArrayList<ak.task.Task> tasks) {
        if (isGuiMode) {
            outputBuffer.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                outputBuffer.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        } else {
            showLine();
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
            showLine();
        }
    }
}
