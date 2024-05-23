package com.storeApp.service;

import com.storeApp.models.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addNewOrder(Order order);
    Optional<Order> getProductById(long id);
    List<Order> getOrderList();
    boolean deleteOrder(long id);
    Order updateOrder(Order editedOrder, long id);
}
