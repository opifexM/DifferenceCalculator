package hexlet.code.formatters;

import hexlet.code.ParserStatus;

import java.util.Map;

public final class StylishFormat {
    private StylishFormat() {
    }

    public static String report(Map<String, Map<ParserStatus, Object>> differenceMap) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("{\n");

        for (Map.Entry<String, Map<ParserStatus, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<ParserStatus, Object> valueMap = entry.getValue();

            for (Map.Entry<ParserStatus, Object> valueEntry : valueMap.entrySet()) {
                ParserStatus parserStatus = valueEntry.getKey();
                Object value = valueEntry.getValue();
                String type = switch (parserStatus) {
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
