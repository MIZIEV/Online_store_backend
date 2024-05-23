package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.storeApp.models.order.Order;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "main_picture_URL")
    private String mainPictureURL;
    @Column(name = "operation_system")
    private String os;
    @Column(name = "os_verion")
    private String osVersion;
    @Column(name = "screen_size")
    private Double screenSize;
    @Column(name = "resolution")
    private String resolution;
    @Column(name = "main_camera")
    private String mainCamera;
    @Column(name = "front_camera")
    private Short frontCamera;
    @Column(name = "processor")
    private String processor;
    @Column(name = "count_of_cores")
    private Byte countOfCores;
    @Column(name = "ram")
    private Short ram;
    @Column(name = "weight")
    private Short weight;
    @Column(name = "battary_capacity")
    private Short batteryCapacity;
    @Column(name = "count_of_sim_card")
    private Byte countOfSimCard;
    @Column(name = "price")
    private Double price;
    @Column(name = "vote_count")
    private Long voteCount;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Column(name = "is_used")
    private boolean isUsed;

    @Transient
    private Double rating;

    @ManyToMany(mappedBy = "phoneList")
    @JsonIgnore
    private List<Order> orders;

    @ManyToMany
    @JoinTable(name = "phone_color",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PhoneRating> ratings;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PhoneRom> romList;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PhonePictureUrl> phonePictureUrls;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<MobileCommunicationStandard> communicationStandardList;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OtherFeatures> featuresList;

    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Description> descriptionList;

    public Phone() {}

    public Phone(Long id, String model, String mainPictureURL, String os, String osVersion, Double screenSize,
                 String resolution, String mainCamera, Short frontCamera, String processor, Byte countOfCores,
                 Short ram, Short weight, Short batteryCapacity, Byte countOfSimCard, Double price,
                 Long voteCount, Brand brand, boolean isUsed,
                 List<PhonePictureUrl> phonePictureUrls, List<MobileCommunicationStandard> communicationStandardList,
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
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.countOfSimCard = countOfSimCard;
        this.price = price;
        this.voteCount = voteCount;
        this.brand = brand;
        this.isUsed = isUsed;
        this.phonePictureUrls = phonePictureUrls;
        this.communicationStandardList = communicationStandardList;
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

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
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

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public List<PhoneRom> getRomList() {
        return romList;
    }

    public void setRomList(List<PhoneRom> romList) {
        this.romList = romList;
    }

    public List<PhonePictureUrl> getPhonePictureURLS() {
        return phonePictureUrls;
    }

    public void setPhonePictureURLS(List<PhonePictureUrl> phonePictureUrls) {
        this.phonePictureUrls = phonePictureUrls;
    }

    public List<MobileCommunicationStandard> getCommunicationStandardList() {
        return communicationStandardList;
    }

    public void setCommunicationStandardList(List<MobileCommunicationStandard> standartList) {
        this.communicationStandardList = standartList;
    }

    public List<OtherFeatures> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<OtherFeatures> featuresList) {
        this.featuresList = featuresList;
    }

    public List<Description> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<Description> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public List<PhonePictureUrl> getPhonePictureUrls() {
        return phonePictureUrls;
    }

    public void setPhonePictureUrls(List<PhonePictureUrl> phonePictureUrls) {
        this.phonePictureUrls = phonePictureUrls;
    }

    public List<PhoneRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<PhoneRating> ratings) {
        this.ratings = ratings;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}