package hexlet.code.formatters;

import hexlet.code.ParserObject;
import hexlet.code.ParserStatus;
import hexlet.code.exception.ParserFormatError;

import java.util.List;

public final class StylishFormat {
    private StylishFormat() {
    }

    public static String report(List<ParserObject> differenceReport) {
        StringBuilder sbReport = new StringBuilder();
        sbReport.append("{\n");

        for (ParserObject parserObject : differenceReport) {
            String key = parserObject.key();
            ParserStatus status = parserObject.status();
            Object value1 = parserObject.value1();
            Object value2 = parserObject.value2();
            switch (status) {
                case DELETED -> sbReport
                        .append("  - ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                case ADDED -> sbReport
                        .append("  + ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                case UNCHANGED -> sbReport
                        .append("    ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                case CHANGED -> {
                    sbReport.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                    sbReport.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(value2)
                            .append("\n");
                }
                default -> throw new ParserFormatError("Unexpected status value: " + status);
            }
        }
        sbReport.append("}");
        return sbReport.toString();
    }
}
