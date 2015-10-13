package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@SpringBootApplication
public class GeojsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeojsonApplication.class, args);
    }

    /**
     * Sample POST request:
     * <pre>
     *   {
     *  "name" : "pierwszy",
     *  "location" : { "type": "Point", "coordinates": [100.0, 0.0] }
     *  }
     * </pre>
     */
    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
        objectMapper.registerModule(new GeoJsonModule());
        return objectMapper;
    }

    static abstract class GeoJsonPointMixin {
        GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {
        }
    }
}
