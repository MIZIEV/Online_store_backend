package com.storeApp.controllers;

import com.storeApp.dto.OrderDto;
import com.storeApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.storeApp.models.Order;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(orderService.getOrderList(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addNewOrder(@RequestBody  OrderDto orderDto){
        Order order = convertToOrder(orderDto);
        orderService.addNewOrder(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    private Order convertToOrder(OrderDto orderDto){
        Order order = new Order();

        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(orderDto.getStatus());
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderOwner(null);

        return order;
    }

}
