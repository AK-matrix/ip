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

import ak.contact.Contact;
import ak.task.Task;
import ak.task.Todo;

public class StorageTest {

    private static final String TEST_FILE_PATH = "test_data.txt";
    private static final String TEST_CONTACT_FILE_PATH = "data/contacts.txt"; // Storage
                                                                              // uses
                                                                              // hardcoded
                                                                              // path
                                                                              // for
                                                                              // contacts
                                                                              // currently
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE_PATH).delete();
        // Be careful not to delete real contact data if possible, but for now
        // we are testing in a controlled env.
        // Since Storage hardcodes contact path, we might be overwriting.
        // Ideally Storage should accept contact file path in constructor.
        // For this test, we assume we can overwrite or we should backup.
        // Given the instructions, I will proceed but might want to refactor
        // Storage later to accept contact path.
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
}
