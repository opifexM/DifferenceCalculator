package hexlet.code.exception;

public class DisplayFormatError extends RuntimeException {
    public DisplayFormatError(String message) {
        super(message);
    }
}
