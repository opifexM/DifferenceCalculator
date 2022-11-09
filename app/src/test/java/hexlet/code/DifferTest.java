package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {
    private final Path resourceDirectory = Paths.get("src","test","resources");
    private final String absolutePath = resourceDirectory.toFile().getAbsolutePath();


    @Test
    void testResourceDirectory() {
        assertTrue(absolutePath.endsWith("src/test/resources"));
    }

    @Test
    void testDifferStylish1() throws IOException {
        String file1 = "file1.json";
        String file2 = "file2.json";
        String formatName = "stylish";
        String expected = """                
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish2() throws IOException {
        String file1 = "file5.json";
        String file2 = "file0.json";
        String formatName = "stylish";
        String expected = """                
                {
                  - host: hexlet.io
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish3() throws IOException {
        String file1 = "file0.json";
        String file2 = "file6.json";
        String formatName = "stylish";
        String expected = """                
                {
                  + timeout: 20
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish4() throws IOException {
        String file1 = "file0.json";
        String file2 = "file0.json";
        String formatName = "stylish";
        String expected = """                
                {
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish5() throws IOException {
        String file1 = "file3.json";
        String file2 = "file4.json";
        String formatName = "stylish";
        String expected = """                
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }
    @Test
    void testDifferStylishYaml1() throws IOException {
        String file1 = "file7.yml";
        String file2 = "file8.yml";
        String formatName = "stylish";
        String expected = """                
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain1() throws IOException {
        String file1 = "file3.json";
        String file2 = "file4.json";
        String formatName = "plain";
        String expected = """
                Property 'chars2' was updated. From [d, e, f] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [value1, value2]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [2, 3, 4, 5] to [22, 33, 44, 55]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [4, 5, 6]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain2() throws IOException {
        String file1 = "file5.json";
        String file2 = "file0.json";
        String formatName = "plain";
        String expected = """
                Property 'host' was removed
                """;
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain3() throws IOException {
        String file1 = "file0.json";
        String file2 = "file6.json";
        String formatName = "plain";
        String expected = """
                Property 'timeout' was added with value: 20
                """;
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain4() throws IOException {
        String file1 = "file0.json";
        String file2 = "file0.json";
        String formatName = "plain";
        String expected = """
                """;
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson1() throws IOException {
        String file1 = "file3.json";
        String file2 = "file4.json";
        String formatName = "json";
        String expected = """
                {
                  "chars1" : [ "a", "b", "c" ],
                  "chars2" : false,
                  "checked" : true,
                  "default" : [ "value1", "value2" ],
                  "id" : "null",
                  "key2" : "value2",
                  "numbers1" : [ 1, 2, 3, 4 ],
                  "numbers2" : [ 22, 33, 44, 55 ],
                  "numbers4" : [ 4, 5, 6 ],
                  "setting1" : "Another value",
                  "setting2" : 300,
                  "setting3" : "none"
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson2() throws IOException {
        String file1 = "file5.json";
        String file2 = "file0.json";
        String formatName = "json";
        String expected = "{ }";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson3() throws IOException {
        String file1 = "file0.json";
        String file2 = "file6.json";
        String formatName = "json";
        String expected = """
                {
                  "timeout" : 20
                }""";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson4() throws IOException {
        String file1 = "file0.json";
        String file2 = "file0.json";
        String formatName = "json";
        String expected = "{ }";
        String actual = Differ.generate(absolutePath + "/" + file1, absolutePath
                + "/" + file2, formatName);
        assertEquals(expected, actual);
    }
}
