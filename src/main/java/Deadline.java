/**
 * Represents a deadline task (task with a due date).
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline object.
     *
     * @param description The description of the deadline.
     * @param by          The due date/time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline, including its type, status,
     * description, and due date.
     *
     * @return The formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline for file storage.
     * Format: D | status | description | by
     *
     * @return The formatted string for storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
