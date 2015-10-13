package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mszarlinski on 2015-10-05.
 */

final class JsonSerializer {

    private final ObjectMapper objectMapper;

    JsonSerializer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    byte[] toJson(final Object o) {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
