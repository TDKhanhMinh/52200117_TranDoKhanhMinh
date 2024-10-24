package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int id;
    private String name;
    private double price;
    private String description;

    public Product() {
    }

    public Product(int id, String name, double price, String description) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
    }
}
