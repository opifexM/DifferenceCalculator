package hexlet.code;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    void testDifferStylish1() {
        Map<String, Object> fileMap1 = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        Map<String, Object> fileMap2 = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        String actual = Differ.generate(fileMap1, fileMap2, "stylish");
        String expected = """
                
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish2() {
        Map<String, Object> fileMap1 = Map.of("host", "hexlet.io");
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "stylish");
        String expected = """
                
                {
                  - host: hexlet.io
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish3() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of("timeout", 20);
        String actual = Differ.generate(fileMap1, fileMap2, "stylish");
        String expected = """
                
                {
                  + timeout: 20
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish4() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "stylish");
        String expected = """
                
                {
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferStylish5() {
        Map<String, Object> fileMap1 = Map.ofEntries(
                Map.entry("setting1", "Some value"),
                Map.entry("setting2", 200),
                Map.entry("setting3", true),
                Map.entry("key1", "value1"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(2, 3, 4, 5)),
                Map.entry("id", 45),
                Map.entry("default", "null"),
                Map.entry("checked", false),
                Map.entry("numbers3", Arrays.asList(3, 4, 5)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", Arrays.asList("d", "e", "f"))
        );
        Map<String, Object> fileMap2 = Map.ofEntries(
                Map.entry("setting1", "Another value"),
                Map.entry("setting2", 300),
                Map.entry("setting3", "none"),
                Map.entry("key2", "value2"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(22, 33, 44, 55)),
                Map.entry("id", "null"),
                Map.entry("default", Arrays.asList("value1", "value2")),
                Map.entry("checked", true),
                Map.entry("numbers4", Arrays.asList(4, 5, 6)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", false));

        String actual = Differ.generate(fileMap1, fileMap2, "stylish");
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
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain1() {
        Map<String, Object> fileMap1 = Map.ofEntries(
                Map.entry("setting1", "Some value"),
                Map.entry("setting2", 200),
                Map.entry("setting3", true),
                Map.entry("key1", "value1"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(2, 3, 4, 5)),
                Map.entry("id", 45),
                Map.entry("default", "null"),
                Map.entry("checked", false),
                Map.entry("numbers3", Arrays.asList(3, 4, 5)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", Arrays.asList("d", "e", "f"))
        );
        Map<String, Object> fileMap2 = Map.ofEntries(
                Map.entry("setting1", "Another value"),
                Map.entry("setting2", 300),
                Map.entry("setting3", "none"),
                Map.entry("key2", "value2"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(22, 33, 44, 55)),
                Map.entry("id", "null"),
                Map.entry("default", Arrays.asList("value1", "value2")),
                Map.entry("checked", true),
                Map.entry("numbers4", Arrays.asList(4, 5, 6)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", false));

        String actual = Differ.generate(fileMap1, fileMap2, "plain");
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
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain2() {
        Map<String, Object> fileMap1 = Map.of("host", "hexlet.io");
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "plain");
        String expected = """
                
                Property 'host' was removed
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain3() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of("timeout", 20);
        String actual = Differ.generate(fileMap1, fileMap2, "plain");
        String expected = """
                
                Property 'timeout' was added with value: 20
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferPlain4() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "plain");
        String expected = """
                
                """;
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson1() {
        Map<String, Object> fileMap1 = Map.ofEntries(
                Map.entry("setting1", "Some value"),
                Map.entry("setting2", 200),
                Map.entry("setting3", true),
                Map.entry("key1", "value1"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(2, 3, 4, 5)),
                Map.entry("id", 45),
                Map.entry("default", "null"),
                Map.entry("checked", false),
                Map.entry("numbers3", Arrays.asList(3, 4, 5)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", Arrays.asList("d", "e", "f"))
        );
        Map<String, Object> fileMap2 = Map.ofEntries(
                Map.entry("setting1", "Another value"),
                Map.entry("setting2", 300),
                Map.entry("setting3", "none"),
                Map.entry("key2", "value2"),
                Map.entry("numbers1", Arrays.asList(1, 2, 3, 4)),
                Map.entry("numbers2", Arrays.asList(22, 33, 44, 55)),
                Map.entry("id", "null"),
                Map.entry("default", Arrays.asList("value1", "value2")),
                Map.entry("checked", true),
                Map.entry("numbers4", Arrays.asList(4, 5, 6)),
                Map.entry("chars1", Arrays.asList("a", "b", "c")),
                Map.entry("chars2", false));

        String actual = Differ.generate(fileMap1, fileMap2, "json");
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
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson2() {
        Map<String, Object> fileMap1 = Map.of("host", "hexlet.io");
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "json");
        String expected = "{ }";
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson3() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of("timeout", 20);
        String actual = Differ.generate(fileMap1, fileMap2, "json");
        String expected = """
                {
                  "timeout" : 20
                }""";
        assertEquals(expected, actual);
    }

    @Test
    void testDifferJson4() {
        Map<String, Object> fileMap1 = Map.of();
        Map<String, Object> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2, "json");
        String expected = "{ }";
        assertEquals(expected, actual);
    }
}
