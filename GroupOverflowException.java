package oop.object;

public class GroupOverflowException extends Exception {
    public GroupOverflowException(String message) {
        super(message);
    }

    public GroupOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupOverflowException(Throwable cause) {
        super(cause);
    }

    public GroupOverflowException() {
        super();
    }
}
