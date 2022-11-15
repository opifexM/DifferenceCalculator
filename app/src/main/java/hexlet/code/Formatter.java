package hexlet.code;

import hexlet.code.exception.DisplayFormatError;
import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.List;

public final class Formatter {
    private Formatter() {
    }

    public static String selectFormat(List<ParserObject> differenceReport, String format) {
        return switch (format) {
            case "plain" -> PlainFormat.report(differenceReport);
            case "json" -> JsonFormat.report(differenceReport);
            case "stylish" -> StylishFormat.report(differenceReport);
            default -> throw new DisplayFormatError("Style format '" + format + "' do not support.");
        };
    }
}
