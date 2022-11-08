package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

public final class PlainFormat {
    private PlainFormat() {
    }

    public static String report(Map<String, Map<Status, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("\n");

        for (Map.Entry<String, Map<Status, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Status, Object> valueMap = entry.getValue();

            if (valueMap.size() > 1) {
                Object oldValue = createString(valueMap.get(Status.DELETED));
                Object newValue = createString(valueMap.get(Status.ADDED));
                sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            } else {
                for (Map.Entry<Status, Object> objectEntry : valueMap.entrySet()) {
                    Status status = objectEntry.getKey();
                    Object value = objectEntry.getValue();
                    if (status == Status.ADDED) {
                        sbReport
                                .append("Property '")
                                .append(key)
                                .append("' was added with value: ")
                                .append(createString(value))
                                .append("\n");
                    } else if (status == Status.DELETED) {
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
        if (value instanceof String && !value.equals("null")) {
            return "'" + value + "'";
        }
        return value;
    }
}
