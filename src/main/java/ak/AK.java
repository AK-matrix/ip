package ak;

import ak.command.Command;
import ak.exception.AkException;
import ak.parser.Parser;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * AK is a chatbot that allows for basic interaction with the user.
 * It is structured using OOP principles with separate classes for UI, Storage,
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
     * Creates and runs the chatbot instance.
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
                // Keep consistent with original behavior; showLine() was here in example but
                // Parser/Commands usually print their own lines/output.
                // However, AK.java main loop in original task description had this.
                // In my implementation, Commands mostly handle printing wrapped in lines via
                // Ui.
                // So I'll remove the extra showLine here to avoid double lines, except for the
                // input divider line.
            }
        }
        ui.showExit();
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new AK("./data/ak.txt").run();
    }
}
