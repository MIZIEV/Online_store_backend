package com.storeApp.service.implementation;

import com.storeApp.models.order.Order;
import com.storeApp.repository.OrderRepository;
import com.storeApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> getProductById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteOrder(long id) {

        Optional<Order> order = orderRepository.findOrderById(id);

        if (order.isPresent()) {
            orderRepository.delete(orderRepository.findOrderById(id).get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Order updateOrder(Order editedOrder, long id) {

        Order order = null;

        if (orderRepository.findOrderById(id).isPresent()) {
            order = orderRepository.findOrderById(id).get();

            order.setId(id);
            order.setTotalAmount(editedOrder.getTotalAmount());
            order.setStatus(editedOrder.getStatus());

            return order;
        }

        return null;
    }
}
