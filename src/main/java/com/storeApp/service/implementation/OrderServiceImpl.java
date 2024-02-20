package com.storeApp.service.implementation;

import com.storeApp.models.Order;
import com.storeApp.repository.OrderRepository;
import com.storeApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        orderRepository.save(order);
    }

    @Override
    public Order getProductById(long id) {
        return orderRepository.findOrderById(id).get();
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteOrder(long id) {
        orderRepository.delete(orderRepository.findOrderById(id).get());
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
