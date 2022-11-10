package hexlet.code;

import hexlet.code.exception.DisplayFormatError;
import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.Map;

public final class Formatter {
    private Formatter() {
    }

    public static String selectFormat(Map<String, ParserStatus> report, String format,
                                      Map<String, Object> dataMap1, Map<String, Object> dataMap2) {
        return switch (format) {
            case "plain" -> PlainFormat.report(report, dataMap1, dataMap2);
            case "json" -> JsonFormat.report(dataMap2);
            case "stylish" -> StylishFormat.report(report, dataMap1, dataMap2);
            default -> throw new DisplayFormatError("Style format '" + format + "' do not support.");
        };
    }
}
