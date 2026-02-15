package ak.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task (task with a start and end date time).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event object.
     *
     * @param description The description of the event.
     * @param from The start date time in yyyy-MM-dd HHmm format.
     * @param to The end date time in yyyy-MM-dd HHmm format.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);

        if (this.from.isAfter(this.to)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
    }

    /**
     * Constructs a new Event object with LocalDateTime directly (for storage
     * loading).
     *
     * @param description The description of the event.
     * @param from The start date time.
     * @param to The end date time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event, including its type, status,
     * description, and duration.
     *
     * @return The formatted string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.US);
        return "[E]" + super.toString() + " (from: " + from.format(displayFormatter) + " to: "
                + to.format(displayFormatter) + ")";
    }

    /**
     * Returns a string representation of the event for file storage. Format: E
     * | status | description | from | to (yyyy-MM-dd HHmm)
     *
     * @return The formatted string for storage.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(storageFormatter) + " | "
                + to.format(storageFormatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Event event = (Event) obj;
        return from.equals(event.from) && to.equals(event.to);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), from, to);
    }
}
