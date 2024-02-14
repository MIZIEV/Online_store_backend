package com.storeApp.models;

import java.util.Date;
import java.util.Objects;

public class Order {

    private Long id;
    private Long userId;
    private Date createdAt;
    private Double totalAmount;
    private Boolean status;

    public Order() {
    }

    public Order(Long id, Long userId, Date createdAt, Double totalAmount, Boolean status) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, createdAt, totalAmount, status);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;

        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(status, order.status);
    }

    @Override
    public String toString() {
        return id + ")  user id - " + userId + ",  total amount - " + totalAmount + ", status - " + status;
    }
}