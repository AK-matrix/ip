package ak.exception;

/**
 * Signals that an error has occurred in the AK application.
 */
public class AkException extends Exception {
    /**
     * Constructs a new AkException with the specified detailed message.
     *
     * @param message The detailed error message.
     */
    public AkException(String message) {
        super(message);
    }
}
