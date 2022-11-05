package hexlet.code;

import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.Map;

public final class Formatter {
    private Formatter() {
    }

    public static String selectFormat(Map<String, Map<Integer, Object>> differenceMap, String format) {
        return switch (format) {
            case "plain" -> PlainFormat.report(differenceMap);
            case "json" -> JsonFormat.report(differenceMap);
            default -> StylishFormat.report(differenceMap);
        };
    }

}
