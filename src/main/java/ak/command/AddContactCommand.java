package ak.command;

import ak.contact.Contact;
import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to add a contact.
 */
public class AddContactCommand extends Command {
    private Contact contact;

    /**
     * Constructs an AddContactCommand.
     *
     * @param contact The contact to be added.
     */
    public AddContactCommand(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void execute(TaskList tasks, ContactList contacts, Ui ui, Storage storage) {
        if (contacts.getAllContacts().contains(contact)) {
            ui.showError("This contact already exists in your list.");
            return;
        }
        contacts.add(contact);
        storage.saveContacts(contacts.getAllContacts());
        ui.showContactAdded(contact, contacts.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
