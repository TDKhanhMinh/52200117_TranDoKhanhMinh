package com.example.Lab9_10.dto;

import com.example.Lab9_10.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int orderId;
    private double totalPrice;
    private int quantity;
    private List<OrderDetailsDTO> orderDetailList;

    public static OrderDTO toOrderDTO(Orders orders) {
        return OrderDTO.builder()
                .orderId(orders.getOrderId())
                .totalPrice(orders.getTotalPrice())
                .quantity(orders.getQuantity())
                .orderDetailList(orders.getOrderDetailList().stream().map(OrderDetailsDTO::toDetails).toList())
                .build();
    }
}
