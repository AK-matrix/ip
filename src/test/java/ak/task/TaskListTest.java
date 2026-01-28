package ak.task;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void find_keyword_returnsMatchingTasks() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.add(new Todo("return book"));
        taskList.add(new Todo("eat apple"));

        ArrayList<Task> result = taskList.find("book");
        assertEquals(2, result.size());
        assertEquals("[T][ ] read book", result.get(0).toString());
        assertEquals("[T][ ] return book", result.get(1).toString());
    }

    @Test
    public void find_nonMatchingKeyword_returnsEmpty() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));

        ArrayList<Task> result = taskList.find("car");
        assertEquals(0, result.size());
    }
}
