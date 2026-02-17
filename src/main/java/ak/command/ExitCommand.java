package ak.command;

import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, ak.contact.ContactList contacts, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
