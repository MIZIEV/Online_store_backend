package com.storeApp.dto;

import java.util.Objects;

public class ProductDto {
    private String brand;
    private String model;
    private String description;
    private String pictureURL;
    private Double price;

    public ProductDto() {}

    public ProductDto(String brand, String model, String description, String pictureURL, Double price) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, description, pictureURL, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductDto productDto = (ProductDto) obj;

        return Objects.equals(brand, productDto.brand) &&
                Objects.equals(model, productDto.model) &&
                Objects.equals(description, productDto.description) &&
                Objects.equals(pictureURL, productDto.pictureURL) &&
                Objects.equals(price, productDto.price);
    }

    @Override
    public String toString() {
        return "brand - " + brand + ", model - " + model + ", description: " + description +
                "\nURL - " + pictureURL +
                "\nprice - " + pictureURL;
    }
}