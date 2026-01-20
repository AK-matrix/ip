import java.util.Scanner;

/**
 * AK is a chatbot that allows for basic interaction with the user.
 * It currently supports greeting, echoing commands, and exiting.
 */
public class AK {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

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
            }
            echo(input);
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
     * Echoes the user's input back to the console with formatting.
     *
     * @param input The user's input string to be echoed.
     */
    public static void echo(String input) {
        printOutput(input);
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
