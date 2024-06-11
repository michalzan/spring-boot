package com.example.demo.items.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    @Getter
    public enum Unit {
        PC("pieces"),
        KG("kilograms");

        private final String name;
        Unit(String name) {
            this.name = name;
        }

    }

}
