package ak.contact;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a list of contacts.
 */
public class ContactList {
    private ArrayList<Contact> contacts;

    /**
     * Constructs an empty ContactList.
     */
    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    /**
     * Constructs a ContactList with an existing list of contacts.
     *
     * @param contacts The list of contacts.
     */
    public ContactList(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Adds a contact to the list.
     *
     * @param contact The contact to add.
     */
    public void add(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Removes a contact from the list at the specified index.
     *
     * @param index The 0-based index of the contact to remove.
     * @return The removed contact.
     */
    public Contact delete(int index) {
        assert index >= 0 && index < contacts.size() : "Contact index is out of bounds";
        return contacts.remove(index);
    }

    /**
     * Retrieves a contact from the list at the specified index.
     *
     * @param index The 0-based index of the contact to retrieve.
     * @return The contact at the specified index.
     */
    public Contact get(int index) {
        assert index >= 0 && index < contacts.size() : "Contact index is out of bounds";
        return contacts.get(index);
    }

    /**
     * Returns the number of contacts in the list.
     *
     * @return The size of the contact list.
     */
    public int size() {
        return contacts.size();
    }

    /**
     * Returns the underlying ArrayList of contacts.
     *
     * @return The ArrayList of contacts.
     */
    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

    /**
     * Returns a stream of contacts.
     *
     * @return A stream of Contact objects.
     */
    public Stream<Contact> stream() {
        return contacts.stream();
    }
}
