package hexlet.code;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private final Path resourceDirectory = Paths.get("src", "test", "resources", "fixtures");
    private final String absolutePath = resourceDirectory.toFile().getAbsolutePath() + "/";

    @ParameterizedTest
    @CsvSource({
        "file0.json, file0.json, stylish, stylish0.txt",
        "file1.json, file2.json, stylish, stylish1.txt",
        "file5.json, file0.json, stylish, stylish2.txt",
        "file0.json, file6.json, stylish, stylish3.txt",
        "file3.json, file4.json, stylish, stylish4.txt",
    })
    void testDifferTypeJsonFormatStylish(String file1, String file2, String format, String fileExpected)
            throws IOException {
        file1 = absolutePath + file1;
        file2 = absolutePath + file2;
        fileExpected = absolutePath + fileExpected;
        String actual = Differ.generate(file1, file2, format);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file3.json, file4.json, plain, plain1.txt",
        "file5.json, file0.json, plain, plain2.txt",
        "file0.json, file6.json, plain, plain3.txt",
        "file0.json, file0.json, plain, plain0.txt"
    })
    void testDifferTypeJsonFormatPlain(String file1, String file2, String format, String fileExpected)
            throws IOException {
        file1 = absolutePath + file1;
        file2 = absolutePath + file2;
        fileExpected = absolutePath + fileExpected;
        String actual = Differ.generate(file1, file2, format);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @Test
    void testDifferTypeJsonFormatDefault() throws IOException {
        String file1 = absolutePath + "file1.json";
        String file2 = absolutePath + "file2.json";
        String fileExpected = absolutePath + "stylish1.txt";
        String actual = Differ.generate(file1, file2);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file3.json, file4.json, json, json1.txt",
        "file5.json, file0.json, json, json0.txt",
        "file0.json, file6.json, json, json2.txt",
        "file0.json, file0.json, json, json0.txt",
    })
    void testDifferTypeJsonFormatJson(String file1, String file2, String format, String fileExpected)
            throws IOException {
        file1 = absolutePath + file1;
        file2 = absolutePath + file2;
        fileExpected = absolutePath + fileExpected;
        String actual = Differ.generate(file1, file2, format);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @Test
    void testDifferTypeYamlFormatStylish() throws IOException {
        String file1 = absolutePath + "file7.yml";
        String file2 = absolutePath + "file8.yml";
        String formatName = "stylish";
        String fileExpected = absolutePath + "stylish5.txt";
        String actual = Differ.generate(file1, file2, formatName);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @Test
    void testDifferTypeYamlFormatPlain() throws IOException {
        String file1 = absolutePath + "file7.yml";
        String file2 = absolutePath + "file8.yml";
        String formatName = "plain";
        String fileExpected = absolutePath + "plain1.txt";
        String actual = Differ.generate(file1, file2, formatName);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @Test
    void testDifferTypeYamlFormatDefault() throws IOException {
        String file1 = absolutePath + "file7.yml";
        String file2 = absolutePath + "file8.yml";
        String fileExpected = absolutePath + "stylish5.txt";
        String actual = Differ.generate(file1, file2);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }

    @Test
    void testDifferTypeYamlFormatJson() throws IOException {
        String file1 = absolutePath + "file7.yml";
        String file2 = absolutePath + "file8.yml";
        String formatName = "json";
        String fileExpected = absolutePath + "json1.txt";
        String actual = Differ.generate(file1, file2, formatName);
        assertEquals(Files.readString(Path.of(fileExpected)), actual);
    }
}
