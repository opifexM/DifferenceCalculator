package hexlet.code.formatters;

import hexlet.code.ParserStatus;
import hexlet.code.exception.ParserFormatError;

import java.util.Map;

public final class StylishFormat {
    private StylishFormat() {
    }

    public static String report(Map<String, ParserStatus> report,
                                Map<String, Object> dataMap1, Map<String, Object> dataMap2) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("{\n");

        for (Map.Entry<String, ParserStatus> entry : report.entrySet()) {
            String key = entry.getKey();
            switch (entry.getValue()) {
                case DELETED -> sbReport
                        .append("  - ")
                        .append(key)
                        .append(": ")
                        .append(dataMap1.get(key))
                        .append("\n");
                case ADDED -> sbReport
                        .append("  + ")
                        .append(key)
                        .append(": ")
                        .append(dataMap2.get(key))
                        .append("\n");
                case UNCHANGED -> sbReport
                        .append("    ")
                        .append(key)
                        .append(": ")
                        .append(dataMap1.get(key))
                        .append("\n");
                case CHANGED -> {
                    sbReport.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(dataMap1.get(key))
                            .append("\n");
                    sbReport.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(dataMap2.get(key))
                            .append("\n");
                }
                default -> throw new ParserFormatError("Unexpected value: " + entry.getValue());
            }
        }
        sbReport.append("}");
        return sbReport.toString();
    }
}
