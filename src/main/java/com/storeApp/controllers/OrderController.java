package com.storeApp.controllers;

import com.storeApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.storeApp.models.Order;

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
    public ResponseEntity<?> addNewOrder(@RequestBody  Order order){
        orderService.addNewOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
