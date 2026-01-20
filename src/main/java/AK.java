/**
 * Represents the entry point of the application.
 * Handles basic user interaction such as greeting and exiting.
 */
public class AK {

    /**
     * Starts the application by printing a greeting message followed
     * by an exit message.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm AK\n"
                + " What can I do for you?";

        String exit = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);
        System.out.println(exit);
    }
}
