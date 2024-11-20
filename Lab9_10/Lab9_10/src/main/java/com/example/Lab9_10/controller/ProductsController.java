package com.example.Lab9_10.controller;

import com.example.Lab9_10.model.Product;
import com.example.Lab9_10.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    public List<Product> getProducts() {
        return productsService.getAllProducts();
    }

    @PostMapping
    public Optional<Product> addProduct(@RequestBody Product product) {
        return productsService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productsService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Optional<Product> replaceProduct(@PathVariable("id") int id,
                                            @RequestBody Product product) {
        return productsService.replaceProduct(product, id);
    }

    @PatchMapping("/{id}")
    public Optional<Product> updateProduct(@PathVariable("id") int id,
                                           @RequestBody Product product) {
        Product oldProduct = productsService.getProductById(id);
        if (product.getProductName() != null) {
            oldProduct.setProductName(product.getProductName());
        } else {
            oldProduct.setProductName(oldProduct.getProductName());
        }

        if (product.getProductPrice() != (oldProduct.getProductPrice())) {
            oldProduct.setProductPrice(product.getProductPrice());
        } else {
            oldProduct.setProductPrice(oldProduct.getProductPrice());
        }

        if (product.getProductIllustration() != null) {
            oldProduct.setProductIllustration(product.getProductIllustration());
        } else {
            oldProduct.setProductIllustration(oldProduct.getProductIllustration());
        }

        if (product.getProductDescription() != null) {
            oldProduct.setProductDescription(product.getProductDescription());
        } else {
            oldProduct.setProductDescription(oldProduct.getProductDescription());
        }
        return productsService.updateProduct(oldProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productsService.deleteProductById(id);
    }

}
