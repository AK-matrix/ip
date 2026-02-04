package ak;

import ak.command.Command;
import ak.exception.AkException;
import ak.parser.Parser;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * AK is a chatbot that allows for basic interaction with the user. It is
 * structured using OOP principles with separate classes for UI, Storage,
 * TaskList, Parser, and Commands.
 */
public class AK {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new AK chatbot instance.
     *
     * @param filePath The file path for data storage.
     */
    public AK(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input string.
     * @return The response string from the chatbot.
     */
    public String getResponse(String input) {
        try {
            ui.setGuiMode(true);
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (AkException e) {
            return "OOPS!!! " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Runs the chatbot in CLI mode.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AkException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            } finally {
                // Keep consistent with original behavior
            }
        }
        ui.showExit();
    }

    private static final String FILE_PATH = "./data/ak.txt";

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new AK(FILE_PATH).run();
    }
}
