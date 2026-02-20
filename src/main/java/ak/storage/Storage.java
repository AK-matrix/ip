package ak.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import ak.task.Deadline;
import ak.task.Event;
import ak.task.Task;
import ak.task.Todo;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private static final DateTimeFormatter TASK_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String filePath;
    private final String contactFilePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath        The path to the file where task data is stored.
     * @param contactFilePath The path to the file where contact data is stored.
     */
    public Storage(String filePath, String contactFilePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        assert contactFilePath != null && !contactFilePath.isEmpty() : "Contact file path cannot be null or empty";
        this.filePath = filePath;
        this.contactFilePath = contactFilePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        File file = new File(filePath);
        ensureParentDirectoryExists(file);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = splitLine(scanner.nextLine());
                if (parts.length < 3) {
                    continue; // Skip corrupted parts
                }
                Task task = createTaskFromParts(parts);
                if (task == null) {
                    continue;
                }
                if ("1".equals(parts[1])) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        assert tasks != null : "Task list to save cannot be null";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Loads contacts from the file.
     *
     * @return An ArrayList of Contact objects loaded from the file.
     */
    public ArrayList<ak.contact.Contact> loadContacts() {
        assert contactFilePath != null && !contactFilePath.isEmpty() : "Contact file path cannot be null or empty";
        ArrayList<ak.contact.Contact> contacts = new ArrayList<>();
        File file = new File(contactFilePath);

        ensureParentDirectoryExists(file);

        if (!file.exists()) {
            return contacts;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Use limit -1 to preserve trailing empty strings (e.g. empty info/phone)
                String[] parts = splitLine(line);

                if (!isValidContactParts(parts)) {
                    continue; // Skip corrupted parts or non-contact lines
                }

                contacts.add(createContactFromParts(parts));
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts file: " + e.getMessage());
        }

        return contacts;
    }

    /**
     * Ensures the parent directory for the given file exists, creating it if necessary.
     *
     * @param file The file whose parent directory should be checked.
     */
    private void ensureParentDirectoryExists(File file) {
        assert file != null : "File reference cannot be null";
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Splits a persisted line into parts using the common delimiter.
     *
     * @param line The persisted line.
     * @return The split parts.
     */
    private String[] splitLine(String line) {
        assert line != null : "Persisted line cannot be null";
        return line.split(" \\| ", -1);
    }

    /**
     * Creates a Task instance from the persisted parts.
     *
     * @param parts The split parts of a task line.
     * @return The constructed Task, or null if type is unknown or incomplete.
     */
    private Task createTaskFromParts(String[] parts) {
        assert parts != null && parts.length >= 3 : "Task parts must contain at least type, done flag, and description";
        String type = parts[0];
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            if (parts.length >= 4) {
                return new Deadline(description, LocalDateTime.parse(parts[3], TASK_DATE_TIME_FORMATTER));
            }
            break;
        case "E":
            if (parts.length >= 5) {
                return new Event(description, LocalDateTime.parse(parts[3], TASK_DATE_TIME_FORMATTER),
                    LocalDateTime.parse(parts[4], TASK_DATE_TIME_FORMATTER));
            }
            break;
        default:
            // Unknown task type, skip
            break;
        }

        return null;
    }

    /**
     * Validates the persisted contact parts.
     *
     * @param parts The split parts of a contact line.
     * @return True if the parts represent a valid contact entry.
     */
    private boolean isValidContactParts(String[] parts) {
        assert parts != null : "Contact parts cannot be null";
        return parts.length >= 5 && "C".equals(parts[0]);
    }

    /**
     * Creates a Contact instance from the persisted parts.
     *
     * @param parts The split parts of a contact line.
     * @return The constructed Contact.
     */
    private ak.contact.Contact createContactFromParts(String[] parts) {
        String name = parts[1];
        String email = parts[2];
        String phone = parts[3];
        String info = parts[4];

        return new ak.contact.Contact(name, email, phone, info);
    }

    /**
     * Saves the list of contacts to the file.
     *
     * @param contacts The list of contacts to save.
     */
    public void saveContacts(ArrayList<ak.contact.Contact> contacts) {
        File file = new File(contactFilePath);
        try (FileWriter writer = new FileWriter(file)) {
            for (ak.contact.Contact contact : contacts) {
                writer.write(contact.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts file: " + e.getMessage());
        }
    }
}
