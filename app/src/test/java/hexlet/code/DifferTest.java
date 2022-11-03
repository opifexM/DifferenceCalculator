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
}
