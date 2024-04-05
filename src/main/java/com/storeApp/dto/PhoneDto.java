package com.storeApp.dto;


import com.storeApp.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Set;

public class PhoneDto {

    private Long id;

    @NotNull(message = "The field `model` mustn't be null!")
    @Size(min = 5, message = "The field `model` must be longer than 5 characters!")
    private String model;

    @NotNull(message = "The field `mainPictureURL` mustn't be null!")
    @URL(message = "Invalid URL, check the correctness of your url! ")
    private String mainPictureURL;

    @NotNull(message = "The field `os` mustn't be null!")
    @Size(min = 5, message = "The field `os` must be longer than 5 characters!")
    private String os;

    @NotNull(message = "The field `osVersion` mustn't be null!")
    @Size(min = 1, message = "The field `osVersion` must be longer than 1 character!")
    private String osVersion;

    @NotNull(message = "The field `screenSize` mustn't be null!")
    @Positive(message = "The screenSize field mustn't be a negative value or equal to zero!")
    @Max(value = 10,message = "The screenSize field mustn't be bigger than 10!")
    private Double screenSize;

    @NotNull(message = "The field `resolution` mustn't be null!")
    @Size(min = 7, message = "The field `resolution` must be longer than 7 characters!")
    private String resolution;

    @NotNull(message = "The field `mainCamera` mustn't be null!")
    @Size(min = 1, message = "The field `mainCamera` must be longer than 1 character!")
    private String mainCamera;

    @NotNull(message = "The field `frontCamera` mustn't be null!")
    @Positive(message = "The frontCamera field mustn't be a negative value or equal to zero!")
    @Max(value = 100,message = "The frontCamera field mustn't be bigger than 100!")
    private Short frontCamera;

    @NotNull(message = "The field `processor` mustn't be null!")
    @Size(min = 5, message = "The field `processor` must be longer than 5 characters!")
    private String processor;

    @NotNull(message = "The field `countOfCores` mustn't be null!")
    @Positive(message = "The countOfCores field mustn't be a negative value or equal to zero!")
    @Max(value = 64,message = "The countOfCores field mustn't be bigger than 64!")
    private Byte countOfCores;

    @NotNull(message = "The field `ram` mustn't be null!")
    @Positive(message = "The ram field mustn't be a negative value or equal to zero!")
    @Max(value = 256,message = "The ram field mustn't be bigger than 256!")
    private Short ram;

    @NotNull(message = "The field `rom` mustn't be null!")
    @Positive(message = "The rom field mustn't be a negative value or equal to zero!")
    private Short rom;

    @NotNull(message = "The field `weight` mustn't be null!")
    @Positive(message = "The weight field mustn't be a negative value or equal to zero!")
    private Short weight;

    @NotNull(message = "The field `batteryCapacity` mustn't be null!")
    @Positive(message = "The batteryCapacity field mustn't be a negative value or equal to zero!")
    private Short batteryCapacity;

    @NotNull(message = "The field `countOfSimCard` mustn't be null!")
    @Positive(message = "The countOfSimCard field mustn't be a negative value or equal to zero!")
    @Max(value = 4,message = "The countOfSimCard field mustn't be bigger than 4!")
    private Byte countOfSimCard;

    @NotNull(message = "The field `price` mustn't be null!")
    @Positive(message = "The price mustn't be a negative value or equal to zero!")
    private Double price;

    @Positive(message = "The rating mustn't be a negative value or equal to zero!")
    @Max(value = 5,message = "The rating field mustn't be bigger than 5!")
    private Double rating;

    private Long voteCount;

    @NotNull(message = "The field `description` mustn't be null!")
    @Size(min = 5, message = "The field `description` must be longer than 5 characters!")
    private String description;

    @NotNull(message = "The field `brand` mustn't be null!")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull(message = "The field `isUsed` mustn't be null!")
    private boolean isUsed;

    private Set<Color> colors;
    private List<PhonePictureURL> phonePictureURLS;
    private List<MobileCommunicationStandard> standardList;
    private List<OtherFeatures> featuresList;

    public PhoneDto() {}

    public PhoneDto(Long id, String model, String mainPictureURL, String os, String osVersion, Double screenSize,
                    String resolution, String mainCamera, Short frontCamera, String processor, Byte countOfCores,
                    Short ram, Short rom, Short weight, Short batteryCapacity, Byte countOfSimCard, Double price,
                    Double rating, Long voteCount, String description, Brand brand, boolean isUsed,
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
        this.phonePictureURLS = phonePictureURLS;
        this.standardList = standartList;
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

    public List<PhonePictureURL> getPhonePictureURLS() {
        return phonePictureURLS;
    }

    public void setPhonePictureURLS(List<PhonePictureURL> phonePictureURLS) {
        this.phonePictureURLS = phonePictureURLS;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public List<MobileCommunicationStandard> getStandardList() {
        return standardList;
    }

    public void setStandardList(List<MobileCommunicationStandard> standardList) {
        this.standardList = standardList;
    }

    public List<OtherFeatures> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<OtherFeatures> featuresList) {
        this.featuresList = featuresList;
    }
}