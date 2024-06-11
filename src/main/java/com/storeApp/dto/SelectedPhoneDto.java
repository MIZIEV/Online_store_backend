package com.storeApp.dto;

import com.storeApp.models.Color;
import com.storeApp.models.PhoneRom;

public class SelectedPhoneDto {

    private Long id;
    private String brand;
    private String model;
    private Color color;
    private PhoneRom rom;
    private Double price;
    private Short quantity;

    public SelectedPhoneDto() {}

    public SelectedPhoneDto(Long id, String brand, String model, Color color, PhoneRom rom, Double price, Short quantity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.rom = rom;
        this.price = price;
        this.quantity = quantity;
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
}