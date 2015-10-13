package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author mszarlinski on 2015-10-13.
 */
public interface ShopRepository extends MongoRepository<Shop, String> {
}
