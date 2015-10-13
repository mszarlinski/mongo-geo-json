package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mszarlinski on 2015-10-13.
 */
@RequestMapping(value = "/shop")
@RestController
public class ShopController {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopController(final ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Shop saveShop(@RequestBody final Shop shop) {
        return shopRepository.insert(shop);
    }
}
