package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.ParserObject;
import netscape.javascript.JSException;

import java.util.List;

public final class JsonFormat {
    private JsonFormat() {
    }

    public static String report(List<ParserObject> differenceReport) {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return jsonMapper.writeValueAsString(differenceReport);
        } catch (JsonProcessingException e) {
            throw new JSException(e);
        }
    }
}
