package hexlet.code.exception;

public class FormatError extends RuntimeException {
    public FormatError(String message) {
        super(message);
    }
}
