package ak.command;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to list all contacts.
 */
public class ListContactCommand extends Command {

    @Override
    public void execute(TaskList tasks, ContactList contacts, Ui ui, Storage storage) {
        ui.showContacts(contacts.getAllContacts());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
