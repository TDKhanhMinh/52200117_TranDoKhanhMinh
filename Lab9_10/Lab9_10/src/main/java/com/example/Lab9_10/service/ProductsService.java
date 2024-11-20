package com.example.Lab9_10.service;

import com.example.Lab9_10.model.Product;
import com.example.Lab9_10.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepo productsRepo;

    public List<Product> getAllProducts() {
        return productsRepo.findAll();
    }

    public Product getProductById(int id) {
        return productsRepo.findById(id).get();
    }

    public void deleteProductById(int id) {
        productsRepo.deleteById(id);
    }

    public Optional<Product> createProduct(Product product) {
        return Optional.of(productsRepo.save(product));
    }

    public Optional<Product> replaceProduct(Product product, int id) {
        if (productsRepo.findById(id).isPresent()) {
            Product oldProduct = productsRepo.findById(id).get();
            oldProduct.setProductName(product.getProductName());
            oldProduct.setProductDescription(product.getProductDescription());
            oldProduct.setProductIllustration(product.getProductIllustration());
            oldProduct.setProductPrice(product.getProductPrice());
            productsRepo.save(oldProduct);
        }
        return Optional.of(productsRepo.findById(id).get());
    }

    public Optional<Product> updateProduct(Product product) {
        productsRepo.save(product);
        return productsRepo.findById(product.getProductId());
    }

}
