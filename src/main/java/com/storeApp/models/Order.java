package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User orderOwner;

    public Order() {}

    public Order(Long id, Date createdAt, Double totalAmount, Boolean status) {
        this.id = id;
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

    public User getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(User user) {
        this.orderOwner = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, totalAmount, status, orderOwner);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;

        return Objects.equals(id, order.id) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(orderOwner, order.orderOwner) &&
                Objects.equals(status, order.status);
    }

    @Override
    public String toString() {
        return id + ") " + ",  total amount - " + totalAmount + ", status - " + status;
    }
}