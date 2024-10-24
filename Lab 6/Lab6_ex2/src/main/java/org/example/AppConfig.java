package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Product product1() {
        Product product = new Product();
        product.setId(1);
        product.setName("Samsung");
        product.setPrice(100.0);
        product.setDescription("Description of Product One");
        return product;
    }
    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Xiaomi", 200.0, "Description of Product Two");
    }

    @Bean
    public Product product3() {
        return new Product(3, "Oneplus", 300.0, "Description of Product Three");
    }

}
