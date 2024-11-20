package com.example.Lab9_10.service;

import com.example.Lab9_10.dto.OrderDTO;
import com.example.Lab9_10.model.Orders;
import com.example.Lab9_10.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrdersRepo ordersRepo;

    public List<OrderDTO> getAllOrders() {
        return ordersRepo.findAll().stream().map(OrderDTO::toOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrdersById(int id) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not find order"));
        return OrderDTO.toOrderDTO(orders);
    }


    public void deleteOrdersById(int id) {
        ordersRepo.deleteById(id);
    }

    public Orders createOrders(Orders orders) {
        return ordersRepo.save(orders);
    }

    public Orders updateOrders(int id, Orders orders) {
        Orders oldOrders = ordersRepo.findOrdersByOrderId(id);
        oldOrders.setOrderId(orders.getOrderId());
        oldOrders.setTotalPrice(orders.getTotalPrice());
        oldOrders.setOrderDetailList(orders.getOrderDetailList());
        return ordersRepo.save(orders);
    }
}
