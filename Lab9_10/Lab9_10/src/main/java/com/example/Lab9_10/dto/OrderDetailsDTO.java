package com.example.Lab9_10.dto;

import com.example.Lab9_10.model.OrdersDetails;
import com.example.Lab9_10.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private int orderDetailsId;
    private int quantity;
    private double totalPrice;
    private ProductDTO product;

    public static OrderDetailsDTO toDetails(OrdersDetails ordersDetails) {
        return OrderDetailsDTO.builder()
                .orderDetailsId(ordersDetails.getOrderDetailsId())
                .quantity(ordersDetails.getQuantity())
                .totalPrice(ordersDetails.getTotalPrice())
                .product(ProductDTO.mapToDTO(ordersDetails.getProduct()))
                .build();
    }
}
