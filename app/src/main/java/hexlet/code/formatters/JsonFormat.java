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

    public static String report(Map<String, Object> dataMapReadOnly) {
        Map<String, Object> dataMap = new TreeMap<>();
        for (Map.Entry<String, Object> entry : dataMapReadOnly.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            dataMap.put(key, value == "null" ? null : value);
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return jsonMapper.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
            throw new JSException(e);
        }
    }
}
