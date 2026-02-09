package ak.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactListTest {
    @Test
    public void add_addContact_success() {
        ContactList contactList = new ContactList();
        assertEquals(0, contactList.size());
        contactList.add(new Contact("Name", "Email", "Phone", "Info"));
        assertEquals(1, contactList.size());
    }

    @Test
    public void delete_deleteContact_success() {
        ContactList contactList = new ContactList();
        contactList.add(new Contact("Name", "Email", "Phone", "Info"));
        contactList.add(new Contact("Name2", "Email2", "Phone2", "Info2"));
        assertEquals(2, contactList.size());

        contactList.delete(0);
        assertEquals(1, contactList.size());
        assertEquals("Name2", contactList.get(0).getName());
    }
}
