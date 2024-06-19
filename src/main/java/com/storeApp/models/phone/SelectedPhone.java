package com.storeApp.models.phone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storeApp.models.Color;
import com.storeApp.models.PhoneRom;
import com.storeApp.models.order.Order;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "selected_phone")
public class SelectedPhone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Short quantity;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "rom_id")
    private PhoneRom rom;

    @ManyToMany(mappedBy = "phoneList",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    public SelectedPhone() {
    }

    public SelectedPhone(Long id, String brand, String model, Double price, Short quantity, Color color, PhoneRom rom) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.rom = rom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PhoneRom getRom() {
        return rom;
    }

    public void setRom(PhoneRom rom) {
        this.rom = rom;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}