package hexlet.code;

import hexlet.code.exception.ParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    private Parser() {
    }

    public static Map<String, String> start(File fileName) {
        return readFile(fileName);
    }

    protected static Map<String, String> readFile(File file) {
        TreeMap<String, String> fileMap = new TreeMap<>();
        try (FileReader in = new FileReader(file);
             BufferedReader reader = new BufferedReader(in)) {
            while (reader.ready()) {
                String line = reader.readLine();
                parse(line, fileMap);
            }
        } catch (IOException e) {
            throw new ParserException(e.getMessage());
        }
        return fileMap;
    }

    protected static void parse(String content, Map<String, String> fileMap) {
        String parseLine = content.replaceAll("[\",{}]", "");
        int separatorIndex = parseLine.indexOf(":");
        if (separatorIndex > 0) {
            String key = parseLine.substring(0, separatorIndex).trim();
            String value = parseLine.substring(separatorIndex + 1).trim();
            fileMap.put(key, value);
        }
    }
}
