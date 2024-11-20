package com.example.Lab9_10.controller;

import com.example.Lab9_10.dto.OrderDTO;
import com.example.Lab9_10.model.Orders;
import com.example.Lab9_10.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;


    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Orders createOrders(@RequestBody Orders orders) {
        return orderService.createOrders(orders);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrdersById(@PathVariable("id") int id) {
        System.out.println(orderService.getOrdersById(id));
        return orderService.getOrdersById(id);
    }

    @PutMapping("/{id}")
    public Orders updateOrdersById(@PathVariable("id") int id, @RequestBody Orders orders) {
        return orderService.updateOrders(id, orders);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdersById(@PathVariable("id") int id) {
        orderService.deleteOrdersById(id);
    }


}
