package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {
    private final Path resourceDirectory = Paths.get("src", "test", "resources", "fixtures");
    private final String absolutePath = resourceDirectory.toFile().getAbsolutePath();


    @Test
    void testResourceDirectory() {
        assertTrue(absolutePath.endsWith("src/test/resources/fixtures"));
    }

    @Test
    void testDifferStylish1() throws IOException {
        String file1 = "/file1.json";
        String file2 = "/file2.json";
        String formatName = "stylish";
        String fileExpected = "/stylish1.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferStylish2() throws IOException {
        String file1 = "/file5.json";
        String file2 = "/file0.json";
        String formatName = "stylish";
        String fileExpected = "/stylish2.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferStylish3() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file6.json";
        String formatName = "stylish";
        String fileExpected = "/stylish3.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferStylish4() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file0.json";
        String formatName = "stylish";
        String fileExpected = "/stylish0.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferStylish5() throws IOException {
        String file1 = "/file3.json";
        String file2 = "/file4.json";
        String formatName = "stylish";
        String fileExpected = "/stylish4.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferStylishYaml1() throws IOException {
        String file1 = "/file7.yml";
        String file2 = "/file8.yml";
        String formatName = "stylish";
        String fileExpected = "/stylish5.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferPlain1() throws IOException {
        String file1 = "/file3.json";
        String file2 = "/file4.json";
        String formatName = "plain";
        String fileExpected = "/plain1.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferPlain2() throws IOException {
        String file1 = "/file5.json";
        String file2 = "/file0.json";
        String formatName = "plain";
        String fileExpected = "/plain2.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferPlain3() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file6.json";
        String formatName = "plain";
        String fileExpected = "/plain3.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferPlain4() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file0.json";
        String formatName = "plain";
        String fileExpected = "/plain0.txt";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferJson1() throws IOException {
        String file1 = "/file3.json";
        String file2 = "/file4.json";
        String formatName = "json";
        String fileExpected = "/expect1.json";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferJson2() throws IOException {
        String file1 = "/file5.json";
        String file2 = "/file0.json";
        String formatName = "json";
        String fileExpected = "/expect0.json";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferJson3() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file6.json";
        String formatName = "json";
        String fileExpected = "/expect2.json";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }

    @Test
    void testDifferJson4() throws IOException {
        String file1 = "/file0.json";
        String file2 = "/file0.json";
        String formatName = "json";
        String fileExpected = "/expect0.json";
        String actual = Differ.generate(absolutePath + file1, absolutePath + file2, formatName);
        assertEquals(Files.readString(Path.of(absolutePath + fileExpected)), actual);
    }
}
