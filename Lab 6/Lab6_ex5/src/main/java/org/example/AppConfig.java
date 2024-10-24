package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.product.id}")
    private int id;

    @Value("${app.product.name}")
    private String name;

    @Value("${app.product.price}")
    private double price;

    @Value("${app.product.description}")
    private String description;

    @Bean
    public Product product() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        return product;
    }
}
