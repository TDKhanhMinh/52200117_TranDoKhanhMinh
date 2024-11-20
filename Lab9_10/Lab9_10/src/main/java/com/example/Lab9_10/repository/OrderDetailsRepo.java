package com.example.Lab9_10.repository;

import com.example.Lab9_10.model.OrdersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrdersDetails, Integer> {
}
