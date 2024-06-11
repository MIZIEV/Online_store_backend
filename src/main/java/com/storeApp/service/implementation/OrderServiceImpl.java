package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.User;
import com.storeApp.models.order.Order;
import com.storeApp.repository.OrderRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.OrderService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, PhoneRepository phoneRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewOrder(Order order, String userEmail) {

        User orderOwner = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        order.setOrderOwner(orderOwner);

        List<Phone> managedPhones = order.getPhoneList().stream()
                .map(phone -> {
                    if (phone.getId() != null) {
                        return phoneRepository.findById(phone.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Phone not found"));
                    } else {
                        return phoneRepository.save(phone);
                    }
                }).collect(Collectors.toList());

        order.setPhoneList(managedPhones);
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
    public List<Order> getOrderListForUser(String email) {
        return userRepository.findByEmail(email).get().getOrderList();
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

    @Override
    @Transactional(readOnly = false)
    public String changeCompleteStatus(Long orderId) {

        Order order = null;

        if (orderRepository.findOrderById(orderId).isPresent()) {
            order = orderRepository.findOrderById(orderId).get();
            order.setStatus(!order.getStatus());
            orderRepository.save(order);
            return "Complete status in order with id - " + orderId + " was changed to - " + order.getStatus();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Order with id " + orderId + " not found");
        }
    }
}