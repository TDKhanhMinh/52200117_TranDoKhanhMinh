package com.example.Lab9_10.repository;

import com.example.Lab9_10.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    @Query("select o from Orders o where o.orderId =: id ")
    Orders findOrdersByOrderId(int id);
}
