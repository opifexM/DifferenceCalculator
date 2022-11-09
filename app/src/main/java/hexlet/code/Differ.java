package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Differ {
    private Differ() {
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> fileMap1 = readData(filepath1);
        Map<String, Object> fileMap2 = readData(filepath2);
        Map<String, Map<ParserStatus, Object>> differenceMap = findDifference(fileMap1, fileMap2);
        return Formatter.selectFormat(differenceMap, format);
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
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }

    private static String readFile(String filepath) throws IOException {
        Path path = Path.of(filepath);
        if (!Files.isRegularFile(path) || Files.notExists(path)) {
            throw new IOException("File '" + filepath + "' cannot be read.");
        }
        return Files.readString(path);
    }

    private static Map<String, Map<ParserStatus, Object>> findDifference(Map<String, Object> fileMap1ReadOnly,
                                                                         Map<String, Object> fileMap2ReadOnly) {
        Map<String, Object> fileMap1 = copyOfMap(fileMap1ReadOnly);
        Map<String, Object> fileMap2 = copyOfMap(fileMap2ReadOnly);
        Map<String, Map<ParserStatus, Object>> report = new TreeMap<>();
        for (Map.Entry<String, Object> entry1 : fileMap1.entrySet()) {
            String key1 = entry1.getKey();
            Object value1 = entry1.getValue();
            boolean isKeyExist = fileMap2.containsKey(key1);
            boolean isValueSame = isKeyExist && fileMap2.get(key1).equals(value1);

            if (isKeyExist && isValueSame) {
                addValueToReport(report, key1, value1, ParserStatus.UNCHANGED);
            } else {
                addValueToReport(report, key1, value1, ParserStatus.DELETED);
                if (isKeyExist) {
                    addValueToReport(report, key1, fileMap2.get(key1), ParserStatus.ADDED);
                }
            }
            fileMap2.remove(key1);
        }
        for (Map.Entry<String, Object> entry2 : fileMap2.entrySet()) {
            addValueToReport(report, entry2.getKey(), entry2.getValue(), ParserStatus.ADDED);
        }
        return report;
    }

    private static Map<String, Object> copyOfMap(Map<String, Object> fileMapReadOnly) {
        Map<String, Object> fileMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : fileMapReadOnly.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            fileMap.put(key, value == null ? "null" : value);
        }
        return fileMap;
    }

    private static void addValueToReport(Map<String, Map<ParserStatus, Object>> report, String key, Object value,
                                         ParserStatus parserStatus) {
        report.computeIfAbsent(key, k -> new TreeMap<>());
        report.get(key).put(parserStatus, value);
    }
}
