package ak.command;

import java.util.ArrayList;

import ak.storage.Storage;
import ak.task.Task;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(this.keyword);
        ui.showFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
