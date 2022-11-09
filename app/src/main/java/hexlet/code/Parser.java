package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.exception.ExtensionFileError;

import java.io.IOException;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> start(String data, String fileExtension) throws IOException {
        return switch (fileExtension) {
            case "json" -> readJson(data);
            case "yml" -> readYaml(data);
            default -> throw new ExtensionFileError("File extension '" + fileExtension + "' do not support.");
        };
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
