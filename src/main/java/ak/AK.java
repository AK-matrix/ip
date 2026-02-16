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
 * <p>
 * The application features a "Royal" Game of Thrones theme.
 */
public class AK {

    private static final String FILE_PATH = "./data/ak.txt";
    private static final String CONTACT_FILE_PATH = "./data/contacts.txt";
    private Storage storage;
    private TaskList tasks;
    private ak.contact.ContactList contacts;
    private Ui ui;

    /**
     * Constructs a new AK chatbot instance.
     *
     * @param filePath The file path for task data storage.
     * @param contactFilePath The file path for contact data storage.
     */
    public AK(String filePath, String contactFilePath) {
        ui = new Ui();
        storage = new Storage(filePath, contactFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }

        try {
            contacts = new ak.contact.ContactList(storage.loadContacts());
        } catch (Exception e) {
            ui.showError("Error loading contacts. Starting with an empty contact list.");
            contacts = new ak.contact.ContactList();
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
            c.execute(tasks, contacts, ui, storage);
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
                c.execute(tasks, contacts, ui, storage);
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

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new AK(FILE_PATH, CONTACT_FILE_PATH).run();
    }
}
