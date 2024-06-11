package com.example.demo.items;

import com.example.demo.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Item, String> {

    Optional<Item> findByName(String name);

}
