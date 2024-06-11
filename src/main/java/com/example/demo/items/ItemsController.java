package com.example.demo.items;

import com.example.demo.items.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    private final ItemsService service;

    @Autowired
    public ItemsController(ItemsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping
    public List<Item> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return service.create(item);
    }

    @PutMapping
    public Item update(@RequestBody Item item) {
        return service.update(item);
    }

    @GetMapping("/name")
    public List<Item> getByNameSubstring(@RequestParam("substring") String substring) {
        return service.findByNameSubstring(substring);
    }

    @GetMapping("/namesql")
    public List<Item> getByNameSubstringSql(@RequestParam("substring") String substring) {
        return service.findByNameSubstringSql(substring);
    }

}
