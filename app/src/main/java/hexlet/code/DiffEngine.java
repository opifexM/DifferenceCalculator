package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DiffEngine {
    private DiffEngine() {
    }

    public static List<ParserObject> findDifference(Map<String, Object> dataMap1,
                                                         Map<String, Object> dataMap2) {
        Set<String> keys = new HashSet<>();
        ArrayList<ParserObject> parserObjectsReport = new ArrayList<>();
        keys.addAll(dataMap1.keySet());
        keys.addAll(dataMap2.keySet());
        for (String key : keys) {
            if (!dataMap1.containsKey(key)) {
                parserObjectsReport.add(new ParserObject(key, ParserStatus.ADDED, dataMap2.get(key), null));
            } else if (!dataMap2.containsKey(key)) {
                parserObjectsReport.add(new ParserObject(key, ParserStatus.DELETED, dataMap1.get(key), null));
            } else if (!dataMap1.get(key).equals(dataMap2.get(key))) {
                parserObjectsReport.add(new ParserObject(key, ParserStatus.CHANGED, dataMap1.get(key),
                        dataMap2.get(key)));
            } else {
                parserObjectsReport.add(new ParserObject(key, ParserStatus.UNCHANGED, dataMap1.get(key), null));
            }
        }
        parserObjectsReport.sort(Comparator.comparing(ParserObject::key));
        return parserObjectsReport;
    }
}
