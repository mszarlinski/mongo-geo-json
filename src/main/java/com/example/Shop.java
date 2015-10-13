package com.example;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * @author mszarlinski on 2015-10-13.
 */
@Data
@Builder
@JsonDeserialize(builder = Shop.ShopBuilder.class)
@Document
public class Shop {
    @Id
    private final String id;

    private final String name;

    private final GeoJsonPoint location;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ShopBuilder {
    }
}
