package com.storeApp.repository;

import com.storeApp.models.User;
import com.storeApp.models.order.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderById(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_selected_phone WHERE order_id = :orderId", nativeQuery = true)
    void deleteOrderPhoneByOrderId(@Param("orderId") Long orderId);

    List<Order> findByOrderOwner(User user);

}