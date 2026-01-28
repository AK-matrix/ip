package ak.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task (task with a due date and time).
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline object.
     *
     * @param description The description of the deadline.
     * @param by          The due date and time in yyyy-MM-dd HHmm format.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Constructs a new Deadline object with LocalDateTime directly (for storage
     * loading).
     *
     * @param description The description of the deadline.
     * @param by          The due date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline, including its type, status,
     * description, and due date time.
     *
     * @return The formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

    /**
     * Returns a string representation of the deadline for file storage.
     * Format: D | status | description | by (yyyy-MM-dd HHmm)
     *
     * @return The formatted string for storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
