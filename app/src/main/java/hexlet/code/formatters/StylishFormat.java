package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

public final class StylishFormat {
    private StylishFormat() {
    }

    public static String report(Map<String, Map<Status, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("\n{\n");

        for (Map.Entry<String, Map<Status, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Status, Object> valueMap = entry.getValue();

            for (Map.Entry<Status, Object> valueEntry : valueMap.entrySet()) {
                Status status = valueEntry.getKey();
                Object value = valueEntry.getValue();
                String type = switch (status) {
                    case DELETED -> "  - ";
                    case ADDED -> "  + ";
                    default -> "    ";
                };
                sbReport.append(type)
                        .append(key)
                        .append(": ")
                        .append(value)
                        .append("\n");
            }
        }

        sbReport.append("}\n");
        return sbReport.toString();
    }
}
