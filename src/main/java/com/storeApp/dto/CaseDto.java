package com.storeApp.dto;

import com.storeApp.models.Brand;
import com.storeApp.models.MobileCommunicationStandard;
import com.storeApp.models.OtherFeatures;
import com.storeApp.models.PhonePictureURL;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

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

    public CaseDto(){}

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
}
