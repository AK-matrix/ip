package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class MarkCommandTest {

    @Test
    public void execute_validIndex_success() throws ak.exception.AkException {
        TaskList tasks = new TaskList();
        Task task = new Todo("read book");
        tasks.add(task);

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage("temp_mark_test.txt");

        MarkCommand command = new MarkCommand(0);
        command.execute(tasks, contacts, ui, storage);

        assertEquals("X", task.getStatusIcon());
    }
}
