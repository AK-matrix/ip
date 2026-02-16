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
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the file where data is stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // create directory if it doesn't exist
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ", -1);

                if (parts.length < 3) {
                    continue; // Skip corrupted parts
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        task = new Deadline(description, LocalDateTime.parse(parts[3], formatter));
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        task = new Event(description, LocalDateTime.parse(parts[3], formatter),
                                LocalDateTime.parse(parts[4], formatter));
                    }
                    break;
                default:
                    // Unknown task type, skip
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
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
        ArrayList<ak.contact.Contact> contacts = new ArrayList<>();
        File file = new File("./data/contacts.txt");

        // create directory if it doesn't exist
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            return contacts;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Use limit -1 to preserve trailing empty strings (e.g. empty
                // info/phone)
                String[] parts = line.split(" \\| ", -1);

                if (parts.length < 5 || !parts[0].equals("C")) {
                    continue; // Skip corrupted parts or non-contact lines
                }

                String name = parts[1];
                String email = parts[2];
                String phone = parts[3];
                String info = parts[4];

                contacts.add(new ak.contact.Contact(name, email, phone, info));
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts file: " + e.getMessage());
        }

        return contacts;
    }

    /**
     * Saves the list of contacts to the file.
     *
     * @param contacts The list of contacts to save.
     */
    public void saveContacts(ArrayList<ak.contact.Contact> contacts) {
        File file = new File("./data/contacts.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (ak.contact.Contact contact : contacts) {
                writer.write(contact.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts file: " + e.getMessage());
        }
    }
}
