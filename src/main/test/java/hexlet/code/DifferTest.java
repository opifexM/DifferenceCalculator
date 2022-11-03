package java.hexlet.code;


import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void differTest1() {
        Map<String, String> fileMap1 = Map.of("host", "hexlet.io", "timeout", "50", "proxy", "123.234.53.22", "follow", "false");
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
                }""";
        assertEquals(expected, actual);
    }
}
