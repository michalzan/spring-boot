package com.example.demo.items;

import com.example.demo.items.model.Item;
import com.example.demo.util.model.exceptions.EntityNotFoundException;
import com.example.demo.util.model.exceptions.UnprocessableContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    private final ItemsRepository repository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.repository = itemsRepository;
    }

    public Item get(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found!"));
    }

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Item create(Item item) {
        boolean empty = repository.findByName(item.getName()).isEmpty();
        if (empty) {
            return repository.save(item);
        }
        throw new UnprocessableContentException("Item already exists!");
    }

    public Item update(Item item) {
        if (item.getId() == null) throw new UnprocessableContentException("Item not found!");
        Item oldItem = repository.findById(item.getId()).orElseThrow(() -> new EntityNotFoundException("Item not found!"));
        if (item.getName() != null) oldItem.setName(item.getName());
        if (item.getAmount() != null) oldItem.setAmount(item.getAmount());
        if (item.getUnit() != null) oldItem.setUnit(item.getUnit());

        return repository.save(oldItem);
    }

}
