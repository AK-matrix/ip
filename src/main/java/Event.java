/**
 * Represents an event task (task with a start and end time).
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event object.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
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
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
