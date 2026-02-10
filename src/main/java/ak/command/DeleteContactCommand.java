package ak.command;

import ak.contact.Contact;
import ak.contact.ContactList;
import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to delete a contact.
 */
public class DeleteContactCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteContactCommand.
     *
     * @param index The 0-based index of the contact to delete.
     */
    public DeleteContactCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, ContactList contacts, Ui ui, Storage storage) throws AkException {
        if (index >= 0 && index < contacts.size()) {
            Contact removedContact = contacts.delete(index);
            storage.saveContacts(contacts.getAllContacts());
            ui.showContactDeleted(removedContact, contacts.size());
        } else {
            throw new AkException("Contact number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
