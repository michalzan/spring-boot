package com.example.demo.shop;

import com.example.demo.shop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository repository;

    @Autowired
    public ShopService(ShopRepository repository){
        this.repository = repository;
    }

    public Shop save(Shop shop){
        return repository.save(shop);
    }

    public List<Shop> findAll(){
        return repository.findAll();
    }

}
