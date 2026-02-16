package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ak.contact.Contact;
import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

public class ContactCommandTest {

    private static final String TEMP_TASK_FILE = "temp_tasks_cmd_test.txt";
    private static final String TEMP_CONTACT_FILE = "temp_contact_cmd_test.txt";

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        new java.io.File(TEMP_TASK_FILE).delete();
        new java.io.File(TEMP_CONTACT_FILE).delete();
    }

    @Test
    public void execute_deleteContact_success() throws ak.exception.AkException {
        ContactList contacts = new ContactList();
        contacts.add(new Contact("Alice", "a@a.com", "123", "friend"));

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_TASK_FILE, TEMP_CONTACT_FILE);

        DeleteContactCommand command = new DeleteContactCommand(0);
        command.execute(tasks, contacts, ui, storage);

        assertEquals(0, contacts.size());
        assertTrue(ui.getResponse().contains("removed this contact"), "Should confirm deletion");
    }

    @Test
    public void execute_editContact_success() throws ak.exception.AkException {
        ContactList contacts = new ContactList();
        contacts.add(new Contact("Alice", "a@a.com", "123", "friend"));

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_TASK_FILE, TEMP_CONTACT_FILE);

        EditContactCommand command = new EditContactCommand(0, "Bob", null, null, null);
        command.execute(tasks, contacts, ui, storage);

        assertEquals("Bob", contacts.get(0).getName());
        assertTrue(ui.getResponse().contains("edited this contact"), "Should confirm edit");
    }

    @Test
    public void execute_listContact_success() {
        ContactList contacts = new ContactList();
        contacts.add(new Contact("Alice", "a@a.com", "123", "friend"));

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        ui.setGuiMode(true);
        Storage storage = new Storage(TEMP_TASK_FILE, TEMP_CONTACT_FILE);

        ListContactCommand command = new ListContactCommand();
        command.execute(tasks, contacts, ui, storage);

        assertTrue(ui.getResponse().contains("Alice"), "Should list contact");
    }
}
