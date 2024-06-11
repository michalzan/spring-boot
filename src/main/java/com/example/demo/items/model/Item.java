package com.example.demo.items.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, message = "Name should have minimal of 3 characters!")
    private String name;

    @Min(value = 0, message = "Amount cannot be lower than zero!")
    private Long amount;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    public Item() {
    }

    public Item(String id, String name, long amount, Unit unit) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public enum Unit {
        PC("pieces"),
        KG("kilograms");

        private final String name;
        Unit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
