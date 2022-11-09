package hexlet.code;

import hexlet.code.exception.FormatError;
import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.Map;

public final class Formatter {
    private Formatter() {
    }

    public static String selectFormat(Map<String, Map<ParserStatus, Object>> differenceMap, String format) {
        return switch (format) {
            case "plain" -> PlainFormat.report(differenceMap);
            case "json" -> JsonFormat.report(differenceMap);
            case "stylish" -> StylishFormat.report(differenceMap);
            default -> throw new FormatError("Style format '" + format + "' do not support.");
        };
    }

}
