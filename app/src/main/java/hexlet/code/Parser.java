package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.exception.ExtensionFileError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> start(String data, String fileExtension) throws IOException {
        Map<String, Object> dataMap = switch (fileExtension) {
            case "json" -> readJson(data);
            case "yml" -> readYaml(data);
            default -> throw new ExtensionFileError("File extension '" + fileExtension + "' do not support.");
        };
        return checkNullValues(dataMap);
    }

    private static Map<String, Object> checkNullValues(Map<String, Object> dataMapReadOnly) {
        Map<String, Object> dataMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : dataMapReadOnly.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            dataMap.put(key, value == null ? "null" : value);
        }
        return dataMap;
    }

    private static Map<String, Object> readYaml(String data) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(data, new TypeReference<>() {
        });
    }

    public static Map<String, Object> readJson(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, new TypeReference<>() {
        });
    }
}
