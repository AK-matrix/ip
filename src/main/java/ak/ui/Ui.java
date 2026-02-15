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
        assert scanner != null : "Scanner should be initialized";
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message to the user.
     */
    public void showWelcome() {
        printOutput("I drink and I know things..\nWhat do you need?");
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        printOutput("And now my watch is ended.");
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
        assert message != null : "Error message cannot be null";
        printOutput("The Night is Dark and Full of Errors.\n" + message);
    }

    /**
     * Prints a generic message to the user wrapped in horizontal lines.
     *
     * @param messages The message to print.
     */
    public void printOutput(String... messages) {
        assert messages != null : "Messages cannot be null";
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
        assert tasks != null : "Task list cannot be null";
        if (isGuiMode) {
            outputBuffer.append("Here are the matching tasks in your list:\n");
            java.util.stream.IntStream.range(0, tasks.size())
                    .forEach(i -> outputBuffer.append(i + 1).append(".").append(tasks.get(i)).append("\n"));
        } else {
            showLine();
            System.out.println("     Here are the matching tasks in your list:");
            java.util.stream.IntStream.range(0, tasks.size())
                    .forEach(i -> System.out.println("     " + (i + 1) + "." + tasks.get(i)));
            showLine();
        }
    }

    /**
     * Prints the list of contacts.
     *
     * @param contacts The list of contacts.
     */
    public void showContacts(java.util.ArrayList<ak.contact.Contact> contacts) {
        assert contacts != null : "Contact list cannot be null";
        StringBuilder listOutput = new StringBuilder("Here are the contacts in your list:\n");
        for (int i = 0; i < contacts.size(); i++) {
            listOutput.append(i + 1).append(". ").append("**").append(contacts.get(i).getName()).append("**")
                    .append(" | Email: ").append(contacts.get(i).getEmail()).append(" | Phone: ")
                    .append(contacts.get(i).getPhone()).append(" | Info: ").append(contacts.get(i).getInfo());
            if (i < contacts.size() - 1) {
                listOutput.append("\n");
            }
        }
        printOutput(listOutput.toString());
    }

    /**
     * Prints the contact added confirmation.
     *
     * @param contact The contact that was added.
     * @param size The current size of the contact list.
     */
    public void showContactAdded(ak.contact.Contact contact, int size) {
        printOutput("A new burden for the realm.\nI've added this contact:\n  " + contact + "\n Now you have " + size
                + " contacts in the list.");
    }

    /**
     * Prints the contact deleted confirmation.
     *
     * @param contact The contact that was deleted.
     * @param size The current size of the contact list.
     */
    public void showContactDeleted(ak.contact.Contact contact, int size) {
        printOutput("Sent to the Wall.\nI've removed this contact:\n  " + contact + "\n Now you have " + size
                + " contacts in the list.");
    }

    /**
     * Prints the contact edited confirmation.
     *
     * @param contact The contact that was edited.
     */
    public void showContactEdited(ak.contact.Contact contact) {
        printOutput("A debt paid.\nI've edited this contact:\n  " + contact);
    }
}
