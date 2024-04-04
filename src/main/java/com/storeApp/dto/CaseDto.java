package com.storeApp.dto;

import com.storeApp.models.*;

import java.util.Objects;

public class CaseDto {
    private Long id;
    private String model;
    private Brand brand;
    private String mainPictureUrl;
    private Double price;
    private Long voteCount;
    private Double rating;
    private String description;
    private String material;
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