package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Differ {
    private Differ() {
    }

    public static String generate(Map<String, Object> fileMap1Read, Map<String, Object> fileMap2Read, String format) {
        Map<String, Map<Integer, Object>> differenceMap = findDifference(fileMap1Read, fileMap2Read);
        return Formatter.selectFormat(differenceMap, format);
    }

    private static Map<String, Map<Integer, Object>> findDifference(Map<String, Object> fileMap1Read,
                                                                    Map<String, Object> fileMap2Read) {
        Map<String, Object> fileMap1 = new HashMap<>();
        Map<String, Object> fileMap2 = new HashMap<>();
        for (Map.Entry<String, Object> entry : fileMap1Read.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            fileMap1.put(key, value == null ? "null" : value);
        }
        for (Map.Entry<String, Object> entry : fileMap2Read.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            fileMap2.put(key, value == null ? "null" : value);
        }

        Map<String, Map<Integer, Object>> report = new TreeMap<>();
        for (Map.Entry<String, Object> entry1 : fileMap1.entrySet()) {
            String key1 = entry1.getKey();
            Object value1 = entry1.getValue();
            boolean isKeyExist = fileMap2.containsKey(key1);
            boolean isValueSame = isKeyExist && fileMap2.get(key1).equals(value1);

            if (isKeyExist && isValueSame) {
                addValueToReport(report, key1, value1, 0);
            } else {
                addValueToReport(report, key1, value1, -1);
                if (isKeyExist) {
                    addValueToReport(report, key1, fileMap2.get(key1), 1);
                }
            }
            fileMap2.remove(key1);
        }
        for (Map.Entry<String, Object> entry2 : fileMap2.entrySet()) {
            addValueToReport(report, entry2.getKey(), entry2.getValue(), 1);
        }
        return report;
    }

    private static void addValueToReport(Map<String, Map<Integer, Object>> report, String key, Object value,
                                         int typeIndex) {
        report.computeIfAbsent(key, k -> new TreeMap<>());
        report.get(key).put(typeIndex, value);
    }
}
