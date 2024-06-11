package com.example.demo.users.model;

import com.example.demo.shop.model.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Employee extends User{

    @ManyToOne
    @JsonBackReference
    private Shop placeOfWork;

    @Min(value = 0, message = "Salary can't be lower than zero!")
    private Long salary;

}
