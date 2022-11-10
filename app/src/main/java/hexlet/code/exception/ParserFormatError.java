package hexlet.code.exception;

public class ParserFormatError extends RuntimeException {
    public ParserFormatError(String message) {
        super(message);
    }
}
