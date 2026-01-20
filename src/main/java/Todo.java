/**
 * Represents a todo task (task without a specific time).
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo, including its type, status, and
     * description.
     *
     * @return The formatted string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
