package com.example.Lab9_10;

import com.example.Lab9_10.model.Orders;
import com.example.Lab9_10.model.OrdersDetails;
import com.example.Lab9_10.model.Product;
import com.example.Lab9_10.repository.OrderDetailsRepo;
import com.example.Lab9_10.repository.OrdersRepo;
import com.example.Lab9_10.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab910Application {

    @Autowired
    private ProductsRepo productsService;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    public static void main(String[] args) {
        SpringApplication.run(Lab910Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            Product product1 = productsService.save(new Product("SamsungA01", 2500000.0, "Samsung", "Android"));
//            Product product2 = productsService.save(new Product("SamsungA02", 3500000.0, "Samsung", "Android"));
//            Product product3 = productsService.save(new Product("SamsungA03", 4500000.0, "Samsung", "Android"));
//            Product product4 = productsService.save(new Product("SamsungA04", 5500000.0, "Samsung", "Android"));
//
//
//            Orders orders = new Orders();
//
//            OrdersDetails ordersDetails = new OrdersDetails();
//            ordersDetails.setOrder(orders);
//            ordersDetails.setUnitPrice(product1.getProductPrice());
//            ordersDetails.setProduct(product1);
//            orderDetailsRepo.save(ordersDetails);
//
////            OrdersDetails ordersDetails1 = new OrdersDetails();
////            ordersDetails1.setOrder(orders);
////            ordersDetails1.setUnitPrice(product2.getProductPrice());
////            ordersDetails1.setProduct(product2);
////            orderDetailsRepo.save(ordersDetails1);
//
//            List<OrdersDetails> ordersDetailsList = new ArrayList<>();
//            ordersDetailsList.add(ordersDetails);
//            //ordersDetailsList.add(ordersDetails1);
//
//            orders.setTotalPrice(ordersDetails.getTotalPrice());
//            orders.setQuantity(ordersDetails.getQuantity());
//            orders.setOrderDetailList(ordersDetailsList);
//            ordersRepo.save(orders);
//        };
//    }
}
