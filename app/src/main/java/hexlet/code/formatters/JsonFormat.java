package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.ParserStatus;
import netscape.javascript.JSException;

import java.util.Map;
import java.util.TreeMap;

public final class JsonFormat {
    private JsonFormat() {
    }

    public static String report(Map<String, Map<ParserStatus, Object>> differenceMap) {
        Map<String, Object> reportMap = new TreeMap<>();

        for (Map.Entry<String, Map<ParserStatus, Object>> entry : differenceMap.entrySet()) {
            String key = entry.getKey();
            Map<ParserStatus, Object> valueMap = entry.getValue();
            for (Map.Entry<ParserStatus, Object> valueEntry : valueMap.entrySet()) {
                ParserStatus parserStatus = valueEntry.getKey();
                Object value = valueEntry.getValue();
                if (parserStatus != ParserStatus.DELETED) {
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
