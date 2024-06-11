package com.example.demo.shop;

import com.example.demo.shop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService service;

    @Autowired
    public ShopController(ShopService service) {
        this.service = service;
    }

    @PostMapping
    public Shop create(@RequestBody Shop shop){
        return service.save(shop);
    }

    @GetMapping
    public List<Shop> getAll(){
        return service.findAll();
    }

}
