package ak.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_validDates_success() {
        Event event = new Event("description", "2023-10-10 1000", "2023-10-10 1200");
        assertEquals("[E][ ] description (from: Oct 10 2023, 10:00 AM to: Oct 10 2023, 12:00 PM)", event.toString());
    }

    @Test
    public void constructor_invalidDates_throwsException() {
        assertThrows(DateTimeParseException.class, () ->
                new Event("description", "2023-02-30 1000", "2023-10-10 1200"));
    }

    @Test
    public void constructor_startAfterEnd_throwsException() {
        assertThrows(java.time.format.DateTimeParseException.class, () ->
                new Event("Project meeting", "invalid", "2023-12-02 1400"));
    }
}
