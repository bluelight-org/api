package de.bluelight.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseBuilder {

    private final Map<String, Object> response;
    private final ObjectMapper objectMapper;

    public ResponseBuilder(HttpStatus httpStatus) {
        response = new LinkedHashMap<>();
        objectMapper = new ObjectMapper();
        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("status", httpStatus.value());
        meta.put("timestamp", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ssZ").format(new Date()));
        response.put("meta", meta);
    }

    public String data(Object data) {
        response.put("data", data);
        return build();
    }

    public String error(Object... message) {
        response.put("error", Map.of("message", message));
        return build();
    }

    private String build() {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "500 Internal Server Error";
        }
    }
}
