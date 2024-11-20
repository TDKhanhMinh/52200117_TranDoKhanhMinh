package com.example.Lab9_10.mapper;

import com.example.Lab9_10.dto.OrderDTO;
import com.example.Lab9_10.model.Orders;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders toOrders(OrderDTO orderDTO);

}
