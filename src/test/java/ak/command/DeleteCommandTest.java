package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.exception.AkException;
import ak.storage.Storage;

import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_success() throws AkException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage("temp_delete_test.txt"); // Placeholder
                                                               // path

        DeleteCommand command = new DeleteCommand(0);
        command.execute(tasks, contacts, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] write code", tasks.get(0).toString());
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        Storage storage = new Storage("temp_delete_test.txt");

        DeleteCommand command = new DeleteCommand(0);

        // IndexOutOfBoundsException is expected from TaskList.delete if
        // unchecked,
        // but Command usually validates or TaskList asserts.
        // Let's check implementation. TaskList uses assertions.
        assertThrows(AkException.class, () -> command.execute(tasks, contacts, ui, storage));
    }
}
