package com.storeApp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.storeApp.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class PhoneDto {

    private Long id;
    private String model;
    private String mainPictureURL;
    private String os;
    private String osVersion;
    private Double screenSize;
    private String resolution;
    private String mainCamera;
    private Short frontCamera;
    private String processor;
    private Byte countOfCores;
    private Short ram;
    private Short rom;
    private Short weight;
    private Short batteryCapacity;
    private Byte countOfSimCard;
    private Double price;
    private Double rating;
    private Long voteCount;
    private String description;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    private boolean isUsed;
    private Long categoryId;
    private List<PhonePictureURL> phonePictureURLS;

    private List<MobileCommunicationStandard> standartList;

    private List<OtherFeatures> featuresList;

    public PhoneDto() {}

    public PhoneDto(Long id, String model, String mainPictureURL, String os, String osVersion, Double screenSize,
                    String resolution, String mainCamera, Short frontCamera, String processor, Byte countOfCores,
                    Short ram, Short rom, Short weight, Short batteryCapacity, Byte countOfSimCard, Double price,
                    Double rating, Long voteCount, String description, Brand brand, boolean isUsed, Long categoryId,
                    List<PhonePictureURL> phonePictureURLS, List<MobileCommunicationStandard> standartList,
                    List<OtherFeatures> featuresList) {
        this.id = id;
        this.model = model;
        this.mainPictureURL = mainPictureURL;
        this.os = os;
        this.osVersion = osVersion;
        this.screenSize = screenSize;
        this.resolution = resolution;
        this.mainCamera = mainCamera;
        this.frontCamera = frontCamera;
        this.processor = processor;
        this.countOfCores = countOfCores;
        this.ram = ram;
        this.rom = rom;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.countOfSimCard = countOfSimCard;
        this.price = price;
        this.rating = rating;
        this.voteCount = voteCount;
        this.description = description;
        this.brand = brand;
        this.isUsed = isUsed;
        this.categoryId = categoryId;
        this.phonePictureURLS = phonePictureURLS;
        this.standartList = standartList;
        this.featuresList = featuresList;
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

    public String getMainPictureURL() {
        return mainPictureURL;
    }

    public void setMainPictureURL(String mainPictureURL) {
        this.mainPictureURL = mainPictureURL;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(String mainCamera) {
        this.mainCamera = mainCamera;
    }

    public Short getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(Short frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Byte getCountOfCores() {
        return countOfCores;
    }

    public void setCountOfCores(Byte countOfCores) {
        this.countOfCores = countOfCores;
    }

    public Short getRam() {
        return ram;
    }

    public void setRam(Short ram) {
        this.ram = ram;
    }

    public Short getRom() {
        return rom;
    }

    public void setRom(Short rom) {
        this.rom = rom;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Short getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Short batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Byte getCountOfSimCard() {
        return countOfSimCard;
    }

    public void setCountOfSimCard(Byte countOfSimCard) {
        this.countOfSimCard = countOfSimCard;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<PhonePictureURL> getPhonePictureURLS() {
        return phonePictureURLS;
    }

    public void setPhonePictureURLS(List<PhonePictureURL> phonePictureURLS) {
        this.phonePictureURLS = phonePictureURLS;
    }

    public List<MobileCommunicationStandard> getStandartList() {
        return standartList;
    }

    public void setStandartList(List<MobileCommunicationStandard> standartList) {
        this.standartList = standartList;
    }

    public List<OtherFeatures> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<OtherFeatures> featuresList) {
        this.featuresList = featuresList;
    }
}