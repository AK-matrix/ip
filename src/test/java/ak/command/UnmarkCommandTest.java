package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class UnmarkCommandTest {

    private static final String TEMP_FILE = "temp_unmark_test.txt";
    private static final String TEMP_CONTACT_FILE = "temp_unmark_contacts.txt";

    @AfterEach
    public void tearDown() {
        new File(TEMP_FILE).delete();
        new File(TEMP_CONTACT_FILE).delete();
    }

    @Test
    public void execute_validIndex_success() throws ak.exception.AkException {
        TaskList tasks = new TaskList();
        Task task = new Todo("read book");
        task.markAsDone(); // Setup as done
        tasks.add(task);

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_FILE, TEMP_CONTACT_FILE);

        UnmarkCommand command = new UnmarkCommand(0);
        command.execute(tasks, contacts, ui, storage);

        assertEquals(" ", task.getStatusIcon());
    }
}
