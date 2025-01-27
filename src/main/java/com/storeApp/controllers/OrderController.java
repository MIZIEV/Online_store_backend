package com.storeApp.controllers;

import com.storeApp.dto.OrderDto;
import com.storeApp.service.OrderService;
import com.storeApp.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.storeApp.models.order.Order;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.getOrderList(), HttpStatus.OK);
    }

    @GetMapping("/list/{email}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllOrdersForUser(@PathVariable("email") String email) {
        return new ResponseEntity<>(orderService.getOrderListForUser(email), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {

        Optional<Order> order = orderService.getProductById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            String message = "Error: " + "Order with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error:\n");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append("; ").append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {

            Order order = convertToOrder(orderDto);
            orderService.addNewOrder(order, orderDto.getEmail());

            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderDto editedOrder,
                                         @PathVariable("id") Long id, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error:\n");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append("; ").append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderService.updateOrder(convertToOrder(editedOrder), id), HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeCompleteStatus(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.changeCompleteStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        boolean isDelete = orderService.deleteOrder(id);

        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            String message = "Error: " + "Order with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    private Order convertToOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setCity(orderDto.getCity());
        order.setFullName(orderDto.getFullName());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setDeliveryMethod(orderDto.getDeliveryMethod());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setPhoneList(orderDto.getPhoneList());
        order.setOrderOwner(userService.getUserByPhoneNumber(orderDto.getEmail()));

        return order;
    }
}