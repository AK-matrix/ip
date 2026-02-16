package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class DeleteCommandTest {

    private static final String TEMP_FILE = "temp_delete_test.txt";
    private static final String TEMP_CONTACT_FILE = "temp_delete_contacts.txt";

    @AfterEach
    public void tearDown() {
        new File(TEMP_FILE).delete();
        new File(TEMP_CONTACT_FILE).delete();
    }

    @Test
    public void execute_validIndex_success() throws AkException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_FILE, TEMP_CONTACT_FILE);
        DeleteCommand command = new DeleteCommand(0); // Create test command
        command.execute(tasks, contacts, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] write code", tasks.get(0).toString());
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEMP_FILE, TEMP_CONTACT_FILE);

        DeleteCommand command = new DeleteCommand(0);

        // IndexOutOfBoundsException is expected from TaskList.delete if
        // unchecked,
        // but Command usually validates or TaskList asserts.
        // Let's check implementation. TaskList uses assertions.
        assertThrows(AkException.class, () -> command.execute(tasks, contacts, ui, storage));
    }
}
