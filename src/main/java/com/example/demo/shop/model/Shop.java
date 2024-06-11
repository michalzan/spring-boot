package com.example.demo.shop.model;


import com.example.demo.common.Address;
import com.example.demo.users.model.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, message = "Name should have minimal of 3 characters!")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "placeOfWork", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

}
