package com.storeApp.models;

import java.util.Objects;

public class OrderDetail {
    private Long id;
    private Long orderId;
    private Long categoryId;
    private Integer quantity;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Long orderId, Long categoryId, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, categoryId, quantity);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDetail orderDetail = (OrderDetail) obj;

        return Objects.equals(id, orderDetail.id) &&
                Objects.equals(orderId, orderDetail.orderId) &&
                Objects.equals(categoryId, orderDetail.categoryId) &&
                Objects.equals(quantity, orderDetail.quantity);
    }

    @Override
    public String toString() {
        return id + ")  order id - " + orderId + ", category id - " + categoryId + ", quantity - " + quantity;
    }
}