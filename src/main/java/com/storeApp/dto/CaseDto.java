package com.storeApp.dto;

import com.storeApp.models.phone.Brand;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

public class CaseDto {

    private Long id;

    @NotNull(message = "The field `model` mustn't be null!")
    @Size(min = 5, message = "The field `model` must be longer than 5 characters!")
    private String model;

    @NotNull(message = "The field `brand` mustn't be null!")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull(message = "The field `mainPictureURL` mustn't be null!")
    @URL(message = "Invalid URL, check the correctness of your url! ")
    private String mainPictureUrl;

    @NotNull(message = "The field `price` mustn't be null!")
    @Positive(message = "The price mustn't be a negative value or equal to zero!")
    private Double price;

    private Long voteCount;

    @Positive(message = "The rating mustn't be a negative value or equal to zero!")
    @Max(value = 5,message = "The rating field mustn't be bigger than 5!")
    private Double rating;

    @NotNull(message = "The field `description` mustn't be null!")
    @Size(min = 5, message = "The field `description` must be longer than 5 characters!")
    private String description;

    @NotNull(message = "The field `material` mustn't be null!")
    @Size(min = 4, message = "The field `material` must be longer than 4 characters!")
    private String material;

    @NotNull(message = "The field `producingCountry` mustn't be null!")
    @Size(min = 4, message = "The field `producingCountry` must be longer than 4 characters!")
    private String producingCountry;

    public CaseDto() {
    }

    public CaseDto(Long id, String model, Brand brand, String mainPictureUrl, Double price, Long voteCount,
                   Double rating, String description, String material, String producingCountry) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.mainPictureUrl = mainPictureUrl;
        this.price = price;
        this.voteCount = voteCount;
        this.rating = rating;
        this.description = description;
        this.material = material;
        this.producingCountry = producingCountry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getMainPictureUrl() {
        return mainPictureUrl;
    }

    public void setMainPictureUrl(String mainPictureUrl) {
        this.mainPictureUrl = mainPictureUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, mainPictureUrl, price, voteCount, rating, description,
                material, producingCountry);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CaseDto caseDto = (CaseDto) obj;
        return Objects.equals(id, caseDto.id) &&
                Objects.equals(model, caseDto.model) &&
                Objects.equals(brand, caseDto.brand) &&
                Objects.equals(mainPictureUrl, caseDto.mainPictureUrl) &&
                Objects.equals(price, caseDto.price) &&
                Objects.equals(voteCount, caseDto.voteCount) &&
                Objects.equals(rating, caseDto.rating) &&
                Objects.equals(description, caseDto.description) &&
                Objects.equals(material, caseDto.material) &&
                Objects.equals(producingCountry, caseDto.producingCountry);
    }

    @Override
    public String toString() {
        return id + ") " + model + ", " + brand + ", " + price + ", rating " + rating + ", voteCount - " + voteCount
                + ", " + description + ", " + material + ", " + producingCountry;
    }
}