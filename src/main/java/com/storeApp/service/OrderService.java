package com.storeApp.service;

import com.storeApp.models.Order;

import java.util.List;

public interface OrderService {

    void addNewOrder(Order order);
    Order getProductById(long id);
    List<Order> getOrderList();
    void deleteOrder(long id);
    Order updateOrder(Order editedOrder, long id);
}
