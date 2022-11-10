package hexlet.code.formatters;

import hexlet.code.ParserStatus;
import hexlet.code.exception.ParserFormatError;

import java.util.Map;

public final class PlainFormat {
    private PlainFormat() {
    }

    public static String report(Map<String, ParserStatus> report,
                                Map<String, Object> dataMap1, Map<String, Object> dataMap2) {
        StringBuilder sbReport = new StringBuilder();
        for (Map.Entry<String, ParserStatus> entry : report.entrySet()) {
            String key = entry.getKey();
            switch (entry.getValue()) {
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
                        .append(createStringFormat(dataMap2.get(key)))
                        .append("\n");
                case CHANGED -> sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(createStringFormat(dataMap1.get(key)))
                        .append(" to ")
                        .append(createStringFormat(dataMap2.get(key)))
                        .append("\n");
                default -> throw new ParserFormatError("Unexpected value: " + entry.getValue());
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
