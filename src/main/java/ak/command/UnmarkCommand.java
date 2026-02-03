package ak.command;

import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param index The 0-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AkException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            storage.save(tasks.getAllTasks());
            ui.printOutput("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
        } else {
            throw new AkException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
