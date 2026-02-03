package ak.command;

import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList to operate on.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save/load tasks.
     * @throws AkException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AkException;

    /**
     * Returns whether this command exits the application.
     *
     * @return True if the command exits the application, false otherwise.
     */
    public abstract boolean isExit();
}
