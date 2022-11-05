package hexlet.code.formatters;

import java.util.Map;

public final class PlainFormat {
    private PlainFormat() {
    }

    public static String report(Map<String, Map<Integer, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("\n");

        for (Map.Entry<String, Map<Integer, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Integer, Object> valueMap = entry.getValue();

            if (valueMap.size() > 1) {
                Object oldValue = createString(valueMap.get(-1));
                Object newValue = createString(valueMap.get(1));
                sbReport
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            } else {
                for (Map.Entry<Integer, Object> objectEntry : valueMap.entrySet()) {
                    Integer typeIndex = objectEntry.getKey();
                    Object value = objectEntry.getValue();
                    if (typeIndex == 1) {
                        sbReport
                                .append("Property '")
                                .append(key)
                                .append("' was added with value: ")
                                .append(createString(value))
                                .append("\n");
                    } else if (typeIndex == -1) {
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
