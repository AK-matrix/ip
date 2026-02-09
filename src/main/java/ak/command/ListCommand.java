package ak.command;

import ak.contact.ContactList;
import ak.storage.Storage;
import ak.task.TaskList;
import ak.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, ContactList contacts, Ui ui, Storage storage) {
        StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(i + 1).append(".").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                listOutput.append("\n");
            }
        }
        ui.printOutput(listOutput.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
