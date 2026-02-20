package ak.command;

import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        assert task != null : "Task to add cannot be null";
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, ak.contact.ContactList contacts, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null : "Execution context cannot be null";
        if (tasks.getAllTasks().contains(task)) {
            ui.showError("This task already plagues your list.");
            return;
        }
        tasks.add(task);
        storage.save(tasks.getAllTasks());
        ui.printOutput("A new burden for the realm.\nI've added this task:\n  " + task + "\n Now you have "
                + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
