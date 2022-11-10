package hexlet.code;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class DiffEngine {
    private DiffEngine() {
    }

    public static Map<String, ParserStatus> findDifference(Map<String, Object> dataMap1,
                                                              Map<String, Object> dataMap2) {
        Set<String> keys = new HashSet<>();
        Map<String, ParserStatus> report = new TreeMap<>();
        keys.addAll(dataMap1.keySet());
        keys.addAll(dataMap2.keySet());
        for (String key : keys) {
            if (!dataMap1.containsKey(key)) {
                report.put(key, ParserStatus.ADDED);
            } else if (!dataMap2.containsKey(key)) {
                report.put(key, ParserStatus.DELETED);
            } else if (!dataMap1.get(key).equals(dataMap2.get(key))) {
                report.put(key, ParserStatus.CHANGED);
            } else {
                report.put(key, ParserStatus.UNCHANGED);
            }
        }
        return report;
    }
}
