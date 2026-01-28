/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No execution logic needed, handled by isExit()
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
