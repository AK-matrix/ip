package ak.command;

import ak.contact.Contact;
import ak.contact.ContactList;
import ak.exception.AkException;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to edit a contact.
 */
public class EditContactCommand extends Command {
    private int index;
    private String newName;
    private String newEmail;
    private String newPhone;
    private String newInfo;

    /**
     * Constructs an EditContactCommand.
     *
     * @param index The 0-based index of the contact to edit.
     * @param newName The new name (or null if unchanged).
     * @param newEmail The new email (or null if unchanged).
     * @param newPhone The new phone (or null if unchanged).
     * @param newInfo The new info (or null if unchanged).
     */
    public EditContactCommand(int index, String newName, String newEmail, String newPhone, String newInfo) {
        this.index = index;
        this.newName = newName;
        this.newEmail = newEmail;
        this.newPhone = newPhone;
        this.newInfo = newInfo;
    }

    @Override
    public void execute(TaskList tasks, ContactList contacts, Ui ui, Storage storage) throws AkException {
        if (index >= 0 && index < contacts.size()) {
            Contact contactToEdit = contacts.get(index);

            if (newName != null) {
                contactToEdit.setName(newName);
            }
            if (newEmail != null) {
                contactToEdit.setEmail(newEmail);
            }
            if (newPhone != null) {
                contactToEdit.setPhone(newPhone);
            }
            if (newInfo != null) {
                contactToEdit.setInfo(newInfo);
            }

            storage.saveContacts(contacts.getAllContacts());
            ui.showContactEdited(contactToEdit);
        } else {
            throw new AkException("Contact number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
