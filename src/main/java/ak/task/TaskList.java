package ak.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed task.
     */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Task index is out of bounds";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Task index is out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Search keyword cannot be empty";
        return tasks.stream().filter(task -> task.toString().contains(keyword))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
