package ak.command;

import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The 0-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AkException {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.delete(index);
            storage.save(tasks.getAllTasks());
            ui.printOutput("Noted. I've removed this task:\n  " + removedTask + "\n Now you have " + tasks.size()
                    + " tasks in the list.");
        } else {
            throw new AkException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
