package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> dataMap1 = readData(filepath1);
        Map<String, Object> dataMap2 = readData(filepath2);
        List<ParserObject> differenceReport = DiffEngine.findDifference(dataMap1, dataMap2);
        return Formatter.selectFormat(differenceReport, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> readData(String filepath) throws IOException {
        String fileData = readFile(filepath);
        String fileExtension = readFileExtension(filepath);
        return Parser.start(fileData, fileExtension);
    }

    private static String readFileExtension(String filepath) {
        int indexOf = filepath.lastIndexOf(".");
        return indexOf > 0
                ? filepath.substring(indexOf + 1)
                : "";
    }

    private static String readFile(String filepath) throws IOException {
        Path path = Path.of(filepath);
        if (!Files.isRegularFile(path) || Files.notExists(path)) {
            throw new IOException("File '" + filepath + "' cannot be read.");
        }
        return Files.readString(path);
    }
}
