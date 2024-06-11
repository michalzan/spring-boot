package com.example.demo.users.model;

import com.example.demo.common.Address;
import com.example.demo.items.model.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "app_user")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, message = "Name should have minimal of 3 characters!")
    private String name;

    @Email(message = "Not a valid email!")
    private String email;

    @Pattern(
            regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone number not valid!"
    )
    private String phone;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> wishlist;
}
