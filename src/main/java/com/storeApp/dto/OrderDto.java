package com.storeApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {
    @Positive(message = "The total amount mustn't be a negative value or equal to zero!!!")
    @NotNull(message = "The field `totalAmount` mustn't be null!!!")
    private Double totalAmount;
    @NotNull(message = "The field `status` mustn't be null!!!")
    private Boolean status;
    private Long ownerId;
    private LocalDateTime createdAt;

    public OrderDto() {}

    public OrderDto(Double totalAmount, Boolean status, Long ownerId, LocalDateTime createdAt) {
        this.totalAmount = totalAmount;
        this.status = status;
        this.ownerId = ownerId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount, status, ownerId);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDto orderDto = (OrderDto) obj;

        return Objects.equals(totalAmount, orderDto.totalAmount) &&
                Objects.equals(ownerId, orderDto.ownerId) &&
                Objects.equals(status, orderDto.status);
    }

    @Override
    public String toString() {
        return "Total amount - " + totalAmount + ", owner id - " + ownerId + ", order status - " + status;
    }
}