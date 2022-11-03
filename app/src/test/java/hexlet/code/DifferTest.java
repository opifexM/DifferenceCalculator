package hexlet.code;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    void differTest1() {
        Map<String, String> fileMap1 = Map.of("host", "hexlet.io", "timeout", "50",
                "proxy", "123.234.53.22", "follow", "false");
        Map<String, String> fileMap2 = Map.of("timeout", "20", "verbose", "true", "host", "hexlet.io");
        String actual = Differ.generate(fileMap1, fileMap2);
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  + timeout: 20
                  - timeout: 50
                  + verbose: true
                }
                """;
        assertEquals(expected, actual);
    }
    @Test
    void differTest2() {
        Map<String, String> fileMap1 = Map.of("host", "hexlet.io");
        Map<String, String> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2);
        String expected = """
                {
                  - host: hexlet.io
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void differTest3() {
        Map<String, String> fileMap1 = Map.of();
        Map<String, String> fileMap2 = Map.of("timeout", "20");
        String actual = Differ.generate(fileMap1, fileMap2);
        String expected = """
                {
                  + timeout: 20
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void differTest4() {
        Map<String, String> fileMap1 = Map.of();
        Map<String, String> fileMap2 = Map.of();
        String actual = Differ.generate(fileMap1, fileMap2);
        String expected = """
                {
                }
                """;
        assertEquals(expected, actual);
    }

    @Test
    void differTest5() {
        Map<String, String> fileMap1 = Map.ofEntries(
                Map.entry("setting1","Some value"),
                Map.entry("setting2","200"),
                Map.entry("setting3","true"),
                Map.entry("key1","value1"),
                Map.entry("numbers1","[1, 2, 3, 4]"),
                Map.entry("numbers2","[2, 3, 4, 5]"),
                Map.entry("id","45"),
                Map.entry("default","null"),
                Map.entry("checked","false"),
                Map.entry("numbers3","[3, 4, 5]"),
                Map.entry("chars1","[\"a\", \"b\", \"c\"]"),
                Map.entry("chars2","[\"d\", \"e\", \"f\"]")
        );
        Map<String, String> fileMap2 = Map.ofEntries(
                Map.entry( "setting1","Another value"),
                Map.entry("setting2","300"),
                Map.entry("setting3","none"),
                Map.entry("key2","value2"),
                Map.entry("numbers1","[1, 2, 3, 4]"),
                Map.entry("numbers2","[22, 33, 44, 55]"),
                Map.entry("id","null"),
                Map.entry("default","[\"value1\", \"value2\"]"),
                Map.entry("checked","true"),
                Map.entry("numbers4","[4, 5, 6]"),
                Map.entry("chars1","[\"a\", \"b\", \"c\"]"),
                Map.entry("chars2","false"),
                Map.entry("obj1","""
                        {
                        "nestedKey","value",
                        "isNested",true
                }""")
        );
        String actual = Differ.generate(fileMap1, fileMap2);
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  + timeout: 20
                  - timeout: 50
                  + verbose: true
                }
                """;
        assertEquals(expected, actual);
    }
}
