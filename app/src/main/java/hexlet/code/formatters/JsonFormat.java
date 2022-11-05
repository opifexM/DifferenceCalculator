package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import netscape.javascript.JSException;

import java.util.Map;
import java.util.TreeMap;

public final class JsonFormat {
    private JsonFormat() {
    }

    public static String report(Map<String, Map<Integer, Object>> differenceMap) {
        Map<String, Object> reportMap = new TreeMap<>();

        for (Map.Entry<String, Map<Integer, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Integer, Object> valueMap = entry.getValue();
            for (Map.Entry<Integer, Object> valueEntry : valueMap.entrySet()) {
                Integer typeIndex = valueEntry.getKey();
                Object value = valueEntry.getValue();
                if (typeIndex != -1) {
                    reportMap.put(key, value);
                }
            }
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return jsonMapper.writeValueAsString(reportMap);
        } catch (JsonProcessingException e) {
            throw new JSException(e);
        }
    }
}