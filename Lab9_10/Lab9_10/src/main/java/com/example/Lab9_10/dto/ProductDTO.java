package com.example.Lab9_10.dto;

import com.example.Lab9_10.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Integer id;

    private Integer code;

    private String name;

    private double price;

    private String description;

    private String illustration;

    public static ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .description(product.getProductDescription())
                .price(product.getProductPrice())
                .illustration(product.getProductIllustration())
                .build();
    }
}