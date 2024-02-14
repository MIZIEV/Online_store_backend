package com.storeApp.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String brand;
    private String model;
    private String description;
    private String pictureURL;
    private Double price;
    private Long categoryId;

    public Product() {
    }

    public Product(Long id, String brand, String model, String description, String pictureURL, Double price, Long categoryId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
        this.categoryId = categoryId;
    }

    public long getId() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, description, pictureURL, price, categoryId);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;

        return Objects.equals(id, product.id) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(description, product.description) &&
                Objects.equals(pictureURL, product.pictureURL) &&
                Objects.equals(price, product.price) &&
                Objects.equals(categoryId, product.categoryId);
    }

    @Override
    public String toString() {
        return id + ") " + brand + ", " + model + ", " + description + ", " + pictureURL + "\n " + " price - " + price;
    }
}