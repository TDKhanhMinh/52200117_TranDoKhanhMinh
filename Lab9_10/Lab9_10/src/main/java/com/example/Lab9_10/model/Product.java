package com.example.Lab9_10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    private String productName;
    private double productPrice;
    private String productIllustration;
    private String productDescription;


    public Product(String productName, double productPrice, String productIllustration, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productIllustration = productIllustration;
        this.productDescription = productDescription;
    }
}
