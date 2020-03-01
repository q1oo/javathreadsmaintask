package my.azamat.myexception;

public class OverloadCapacityException extends Exception {
    public OverloadCapacityException() {
    }

    public OverloadCapacityException(String message) {
        super(message);
    }

    public OverloadCapacityException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverloadCapacityException(Throwable cause) {
        super(cause);
    }
}
