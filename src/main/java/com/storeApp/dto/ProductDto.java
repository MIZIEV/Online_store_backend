package com.storeApp.dto;

import com.storeApp.models.ProductCharacteristic;
import com.storeApp.models.Rating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long id;
    @NotNull(message = "The field `brand` mustn't be null!!!")
    @Size(min = 5, message = "The field must be longer than 5 characters!!!")
    private String brand;
    @NotNull(message = "The field `model` mustn't be null!!!")
    @Size(min = 5, message = "The field must be longer than 5 characters!!!")
    private String model;
    @NotNull(message = "The field `description` mustn't be null!!!")
    @Size(min = 5, message = "The field must be longer than 5 characters!!!")
    private String description;
    @NotNull(message = "The field `pictureURL` mustn't be null!!!")
    @URL(message = "Invalid URL, check the correctness of your url!!! ")
    private String pictureURL;
    @NotNull(message = "The field `price` mustn't be null!!!")
    @Positive(message = "The price mustn't be a negative value or equal to zero!!!")
    private Double price;
    private List<Rating> rating;
    private Double totalMark;
    private Integer numberOfMarks;
    private Long categoryId;
    private List<ProductCharacteristic> characteristicList;

    public ProductDto() {}

    public ProductDto(Long id, String brand, String model, String description, String pictureURL, Double price,
                      List<Rating> rating, Double totalMark, Integer numberOfMarks,
                      Long categoryId, List<ProductCharacteristic> characteristicList) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
        this.rating = rating;
        this.totalMark = totalMark;
        this.numberOfMarks = numberOfMarks;
        this.categoryId = categoryId;
        this.characteristicList = characteristicList;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(Double totalMark) {
        this.totalMark = totalMark;
    }

    public Integer getNumberOfMarks() {
        return numberOfMarks;
    }

    public void setNumberOfMarks(Integer numberOfMarks) {
        this.numberOfMarks = numberOfMarks;
    }

    public List<ProductCharacteristic> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(List<ProductCharacteristic> characteristicList) {
        this.characteristicList = characteristicList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, description, pictureURL, price, categoryId);
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
                Objects.equals(price, productDto.price) &&
                Objects.equals(categoryId, productDto.categoryId);
    }

    @Override
    public String toString() {
        return "brand - " + brand + ", model - " + model + ", description: " + description +
                "\nURL - " + pictureURL +
                "\nprice - " + pictureURL;
    }
}