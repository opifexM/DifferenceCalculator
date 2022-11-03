package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, String> start(File fileName) throws IOException {
        return readJson(fileName);
    }

    public static Map<String, String> readJson(File file) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}
