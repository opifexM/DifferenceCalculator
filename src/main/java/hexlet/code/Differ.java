package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Differ {
    private Differ() {
    }

    public static String generate(Map<String, String> fileMap1Read, Map<String, String> fileMap2Read) {
        Map<String, String> fileMap1 = new HashMap<>(fileMap1Read);
        Map<String, String> fileMap2 = new HashMap<>(fileMap2Read);

        Map<String, String> report = new TreeMap<>();
        for (Map.Entry<String, String> entry1 : fileMap1.entrySet()) {
            String key1 = entry1.getKey();
            String value1 = entry1.getValue();
            boolean isKeyExist = fileMap2.containsKey(key1);
            boolean isValueExist = fileMap2.containsValue(value1);
            String reportEntry = key1 + ": " + value1;
            if (isKeyExist && isValueExist) {
                report.put(reportEntry, " ");
            } else {
                report.put(reportEntry, "-");
                if (isKeyExist) {
                    String value2 = fileMap2.get(key1);
                    report.put(key1 + ": " + value2, "+");
                }
            }
            fileMap2.remove(key1);
        }
        for (Map.Entry<String, String> entry2 : fileMap2.entrySet()) {
            String key2 = entry2.getKey();
            String value2 = entry2.getValue();
            report.put(key2 + ": " + value2, "+");
        }

        StringBuilder sbReport = new StringBuilder();
        sbReport.append("{\n");
        for (Map.Entry<String, String> entry : report.entrySet()) {
            sbReport
                    .append("  ")
                    .append(entry.getValue())
                    .append(" ")
                    .append(entry.getKey())
                    .append("\n");
        }
        sbReport.append("}\n");

        return sbReport.toString();
    }
}
