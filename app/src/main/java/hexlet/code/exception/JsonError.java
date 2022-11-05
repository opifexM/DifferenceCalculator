package hexlet.code.exception;

public class JsonError extends RuntimeException {
    public JsonError(String message) {
        super(message);
    }
}
