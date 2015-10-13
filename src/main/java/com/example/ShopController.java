package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST)
    public Shop saveShop(@RequestBody final Shop shop) {
        return shopRepository.save(shop);
    }
}
