package ak.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class FindCommandTest {

    private static final String TEMP_FILE = "temp_find_test.txt";
    private static final String TEMP_CONTACT_FILE = "temp_find_contacts.txt";

    @AfterEach
    public void tearDown() {
        new File(TEMP_FILE).delete();
        new File(TEMP_CONTACT_FILE).delete();
    }

    @Test
    public void execute_keywordFound_showsResults() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_FILE, TEMP_CONTACT_FILE);

        FindCommand command = new FindCommand("book");
        command.execute(tasks, contacts, ui, storage);

        String response = ui.getResponse();
        assertTrue(response.contains("1.[T][ ] read book"), "Response should contain matching task");
    }
}
