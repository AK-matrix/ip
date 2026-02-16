package ak.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ak.task.Task;
import ak.task.Todo;

public class StorageTest {

    private static final String TEST_FILE_PATH = "test_data.txt";
    private static final String TEST_CONTACT_FILE_PATH = "test_contacts.txt";

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH, TEST_CONTACT_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE_PATH).delete();
        new File(TEST_CONTACT_FILE_PATH).delete();
    }

    @Test
    public void load_nonExistentFile_returnsEmptyList() {
        new File(TEST_FILE_PATH).delete();
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void saveAndLoad_validTasks_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] read book", loadedTasks.get(0).toString());
    }

    @Test
    public void load_corruptData_skipsCorruptLines() throws IOException {
        FileWriter writer = new FileWriter(TEST_FILE_PATH);
        writer.write("T | 0 | read book\n");
        writer.write("INVALID | DATA\n"); // Corrupt line
        writer.write("T | 1 | write code\n");
        writer.close();

        ArrayList<Task> tasks = storage.load();
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] read book", tasks.get(0).toString());
        assertEquals("[T][X] write code", tasks.get(1).toString());
    }

    @Test
    public void saveAndLoad_validContacts_success() {
        ArrayList<ak.contact.Contact> contacts = new ArrayList<>();
        contacts.add(new ak.contact.Contact("Alice", "a@a.com", "12345", "Friend"));
        storage.saveContacts(contacts);

        ArrayList<ak.contact.Contact> loadedContacts = storage.loadContacts();
        assertEquals(1, loadedContacts.size());
        assertEquals("Alice", loadedContacts.get(0).getName());
        assertEquals("a@a.com", loadedContacts.get(0).getEmail());
    }
}
