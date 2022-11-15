package hexlet.code.formatters;

import hexlet.code.ParserObject;
import hexlet.code.ParserStatus;
import hexlet.code.exception.ParserFormatError;

import java.util.List;

public final class PlainFormat {
    private PlainFormat() {
    }

    public static String report(List<ParserObject> differenceReport) {
        StringBuilder sbReport = new StringBuilder();
        for (ParserObject parserObject : differenceReport) {
            String key = parserObject.key();
            ParserStatus status = parserObject.status();
            Object value1 = parserObject.value1();
            Object value2 = parserObject.value2();

            switch (status) {
                case UNCHANGED -> {
                }
                case DELETED -> sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
                case ADDED -> sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(createStringFormat(value1))
                        .append("\n");
                case CHANGED -> sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(createStringFormat(value1))
                        .append(" to ")
                        .append(createStringFormat(value2))
                        .append("\n");
                default -> throw new ParserFormatError("Unexpected value status: " + status);
            }
        }
        return sbReport.toString().trim();
    }

    private static Object createStringFormat(Object value) {
        if (value.equals("null")) {
            return value;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Integer) {
            return value;
        } else if (value instanceof Boolean) {
            return value;
        }
        return "[complex value]";
    }
}
