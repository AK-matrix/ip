package ak.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ak.contact.Contact;
import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

public class AddContactCommandTest {

    @Test
    public void execute_duplicateContact_showsError() {
        // Setup
        Contact contact1 = new Contact("Arnav", "a@a.com", "123", "info");
        Contact contact2 = new Contact("arnav", "b@b.com", "456", "other info"); // Same
                                                                                 // name
                                                                                 // (case-insensitive)
        ContactList contacts = new ContactList();
        contacts.add(contact1);

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        ui.setGuiMode(true); // Capture output in buffer
        Storage storage = new Storage("temp_contact_test_storage.txt");

        AddContactCommand command = new AddContactCommand(contact2);
        command.execute(tasks, contacts, ui, storage);

        String response = ui.getResponse();
        assertTrue(response.contains("OOPS!!! This contact already exists"),
                "Response should contain error message: " + response);
        assertEquals(1, contacts.size(), "Contact list size should remain 1");

        // Cleanup
        new File("temp_contact_test_storage.txt").delete();
        new File("data/contacts.txt").delete(); // Storage might create default
                                                // file?
    }
}
