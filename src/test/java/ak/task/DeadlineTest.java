package ak.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_validDate_success() {
        LocalDateTime by = LocalDateTime.of(2022, 12, 12, 18, 0);
        Deadline deadline = new Deadline("return book", by);
        assertEquals("[D][ ] return book (by: Dec 12 2022, 6:00 PM)", deadline.toString());
    }

    @Test
    public void toFileString_validDate_success() {
        LocalDateTime by = LocalDateTime.of(2022, 12, 12, 18, 0);
        Deadline deadline = new Deadline("return book", by);
        assertEquals("D | 0 | return book | 2022-12-12 1800", deadline.toFileString());
    }
}
