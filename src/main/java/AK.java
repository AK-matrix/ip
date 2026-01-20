import java.util.Scanner;

/**
 * AK is a chatbot that allows for basic interaction with the user.
 * It currently supports greeting, adding tasks, listing tasks, and exiting.
 */
public class AK {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String[] tasks = new String[100];
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
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
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
     * @param task The task description to add.
     */
    public static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        printOutput("added: " + task);
    }

    /**
     * Lists all tasks currently in the list.
     */
    public static void listTasks() {
        StringBuilder listOutput = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            listOutput.append(i + 1).append(". ").append(tasks[i]);
            if (i < taskCount - 1) {
                listOutput.append("\n");
            }
        }
        printOutput(listOutput.toString());
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
