package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.ParserObject;
import hexlet.code.ParserStatus;
import hexlet.code.exception.ParserFormatError;
import netscape.javascript.JSException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class JsonFormat {
    private JsonFormat() {
    }

    public static String report(List<ParserObject> differenceReport) {
        Map<String, Object> dataMap = new TreeMap<>();
        for (ParserObject parserObject : differenceReport) {
            String key = parserObject.key();
            ParserStatus status = parserObject.status();
            Object value1 = parserObject.value1();
            Object value2 = parserObject.value2();

            switch (status) {
                case DELETED -> {
                }
                case UNCHANGED, ADDED -> dataMap.put(key, value1 == "null" ? null : value1);
                case CHANGED -> dataMap.put(key, value2 == "null" ? null : value2);
                default -> throw new ParserFormatError("Unexpected value status: " + status);
            }
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
