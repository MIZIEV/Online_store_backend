package com.storeApp.service;

import com.storeApp.models.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addNewOrder(Order order, String userEmail);

    Optional<Order> getProductById(long id);

    List<Order> getOrderList();

    List<Order> getOrderListForUser(String username);

    boolean deleteOrder(long id);

    Order updateOrder(Order editedOrder, long id);

    String changeCompleteStatus(Long orderId);
}