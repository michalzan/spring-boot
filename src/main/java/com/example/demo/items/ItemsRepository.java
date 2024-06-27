package com.example.demo.items;

import com.example.demo.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Item, String> {

    Optional<Item> findByName(String name);
    List<Item> findByNameContains(String substring);

    @Query(value="select i from Item i where i.name like %?1%")
    List<Item> findByNameContainsSql(String substring);

    List<Item> findByIdIn(List<String> ids);

}
