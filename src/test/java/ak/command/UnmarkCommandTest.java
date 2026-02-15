package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class UnmarkCommandTest {

    @Test
    public void execute_validIndex_success() throws ak.exception.AkException {
        TaskList tasks = new TaskList();
        Task task = new Todo("read book");
        task.markAsDone(); // Setup as done
        tasks.add(task);

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage("temp_unmark_test.txt");

        UnmarkCommand command = new UnmarkCommand(0);
        command.execute(tasks, contacts, ui, storage);

        assertEquals(" ", task.getStatusIcon());
    }
}
