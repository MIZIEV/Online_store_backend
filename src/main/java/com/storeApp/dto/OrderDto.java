package com.storeApp.dto;

import com.storeApp.models.Phone;
import com.storeApp.models.order.DeliveryMethod;
import com.storeApp.models.order.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    @Positive(message = "The total amount mustn't be a negative value or equal to zero!!!")
    @NotNull(message = "The field `totalAmount` mustn't be null!!!")
    private Double totalAmount;
    @NotNull(message = "The field `status` mustn't be null!!!")
    private Boolean status;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String city;

    private String fullName;

    private String phoneNumber;

    private String email;

    private List<Phone> phoneList;

    public OrderDto() {
    }

    public OrderDto(Double totalAmount, Boolean status, LocalDateTime createdAt) {
        this.totalAmount = totalAmount;
        this.status = status;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount, status);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDto orderDto = (OrderDto) obj;

        return Objects.equals(totalAmount, orderDto.totalAmount) &&
                Objects.equals(status, orderDto.status);
    }
}