package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class AddCommandTest {

    @Test
    public void execute_duplicateTask_showsError() {
        // Setup
        Task task1 = new Todo("read book");
        Task task2 = new Todo("read book");
        TaskList tasks = new TaskList();
        tasks.add(task1);

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true); // Capture output in buffer
        Storage storage = new Storage("temp_test_storage.txt");

        AddCommand command = new AddCommand(task2);
        command.execute(tasks, contacts, ui, storage);

        String response = ui.getResponse();
        assertTrue(response.contains("This task already plagues your list"),
                "Response should contain error message: " + response);
        assertEquals(1, tasks.size(), "Task list size should remain 1");

        // Cleanup
        new File("temp_test_storage.txt").delete();
    }
}
