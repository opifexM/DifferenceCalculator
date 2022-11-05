package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> start(String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.isRegularFile(path) || Files.notExists(path)) {
            throw new IOException("File '" + fileName + "' cannot be read.");
        }
        File file = new File(path.toUri());
        if (file.getName().endsWith(".json")) {
            return readJson(file);
        } else if (file.getName().endsWith(".yml")) {
            return readYaml(file);

        }
        throw new IOException("File '" + fileName + "' not support.");
    }

    private static Map<String, Object> readYaml(File file) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public static Map<String, Object> readJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}
