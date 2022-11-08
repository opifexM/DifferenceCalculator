package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Status;
import netscape.javascript.JSException;

import java.util.Map;
import java.util.TreeMap;

public final class JsonFormat {
    private JsonFormat() {
    }

    public static String report(Map<String, Map<Status, Object>> differenceMap) {
        Map<String, Object> reportMap = new TreeMap<>();

        for (Map.Entry<String, Map<Status, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<Status, Object> valueMap = entry.getValue();
            for (Map.Entry<Status, Object> valueEntry : valueMap.entrySet()) {
                Status status = valueEntry.getKey();
                Object value = valueEntry.getValue();
                if (status != Status.DELETED) {
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
