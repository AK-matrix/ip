package ak.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.task.Todo;
import ak.ui.Ui;

public class FindCommandTest {

    @Test
    public void execute_keywordFound_showsResults() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("write code"));

        ContactList contacts = new ContactList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage("temp_find_test.txt");

        FindCommand command = new FindCommand("book");
        command.execute(tasks, contacts, ui, storage);

        String response = ui.getResponse();
        assertTrue(response.contains("1.[T][ ] read book"), "Response should contain matching task");
    }
}
