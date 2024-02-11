package com.storeApp.models;

public class Phone {
    private long id;
    private String brand;
    private String model;
    private String description;

    private String pictureURL;
    private double price;

    public Phone(long id, String brand, String model, String description, String pictureURL, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPrice(double price) {
        this.price = price;
    }
}
