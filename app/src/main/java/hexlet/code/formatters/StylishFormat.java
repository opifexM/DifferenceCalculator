package hexlet.code.formatters;

import java.util.Map;

public final class StylishFormat {
    private StylishFormat() {
    }

    public static String report(Map<String, Map<Integer, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("\n{\n");

        for (Map.Entry<String, Map<Integer, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Integer, Object> valueMap = entry.getValue();

            for (Map.Entry<Integer, Object> valueEntry : valueMap.entrySet()) {
                Integer typeIndex = valueEntry.getKey();
                Object value = valueEntry.getValue();
                String type = switch (typeIndex) {
                    case -1 -> "  - ";
                    case 1 -> "  + ";
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
