package com.example.Lab9_10.repository;

import com.example.Lab9_10.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.productId =: id ")
    Product findProductByProductId(int id);
}
