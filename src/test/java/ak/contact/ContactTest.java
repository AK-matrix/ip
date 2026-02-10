package ak.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    public void testStringConversion() {
        Contact contact = new Contact("John Doe", "john@example.com", "12345678", "Friend");
        assertEquals("Name: John Doe | Email: john@example.com | Phone: 12345678 | Info: Friend", contact.toString());
    }

    @Test
    public void testToFileString() {
        Contact contact = new Contact("Jane Doe", "jane@example.com", "87654321", "Coworker");
        assertEquals("C | Jane Doe | jane@example.com | 87654321 | Coworker", contact.toFileString());
    }
}
