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
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, ak.contact.ContactList contacts, Ui ui, Storage storage) {
        if (tasks.getAllTasks().contains(task)) {
            ui.showError("This task already exists in your list.");
            return;
        }
        tasks.add(task);
        storage.save(tasks.getAllTasks());
        ui.printOutput(
                "Got it. I've added this task:\n  " + task + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
