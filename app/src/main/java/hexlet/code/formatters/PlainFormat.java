package hexlet.code.formatters;

import hexlet.code.ParserStatus;

import java.util.Map;

public final class PlainFormat {
    private PlainFormat() {
    }

    public static String report(Map<String, Map<ParserStatus, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();

        for (Map.Entry<String, Map<ParserStatus, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<ParserStatus, Object> valueMap = entry.getValue();

            if (valueMap.size() > 1) {
                Object oldValue = createString(valueMap.get(ParserStatus.DELETED));
                Object newValue = createString(valueMap.get(ParserStatus.ADDED));
                sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            } else {
                for (Map.Entry<ParserStatus, Object> objectEntry : valueMap.entrySet()) {
                    ParserStatus parserStatus = objectEntry.getKey();
                    Object value = objectEntry.getValue();
                    if (parserStatus == ParserStatus.ADDED) {
                        sbReport
                                .append("Property '")
                                .append(key)
                                .append("' was added with value: ")
                                .append(createString(value))
                                .append("\n");
                    } else if (parserStatus == ParserStatus.DELETED) {
                        sbReport
                                .append("Property '")
                                .append(key)
                                .append("' was removed")
                                .append("\n");
                    }
                }
            }
        }
        return sbReport.toString();
    }

    private static Object createString(Object value) {
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
