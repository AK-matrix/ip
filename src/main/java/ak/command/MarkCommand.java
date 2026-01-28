package ak.command;

import ak.task.TaskList;
import ak.ui.Ui;
import ak.storage.Storage;
import ak.exception.AkException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand.
     *
     * @param index The 0-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AkException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            storage.save(tasks.getAllTasks());
            ui.printOutput("Nice! I've marked this task as done:\n  " + tasks.get(index));
        } else {
            throw new AkException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
