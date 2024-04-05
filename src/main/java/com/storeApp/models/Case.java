package com.storeApp.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "case_entity")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "brand")
    private Brand brand;
    @Column(name = "main_picture_url")
    private String mainPictureUrl;
    @Column(name = "price")
    private Double price;
    @Column(name = "vote_count")
    private Long voteCount;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "description")
    private String description;
    @Column(name = "material")
    private String material;
    @Column(name = "producing_country")
    private String producingCountry;

    @ManyToMany
    @JoinTable(name = "case_color",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors;

    public Case(Long id, String model, Brand brand, String mainPictureUrl, Double price, Long voteCount,
                Double rating, String description, String material,
                String producingCountry, Set<Color> colors) {
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
        this.colors = colors;
    }

    public Case() {
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

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, mainPictureUrl, price, voteCount, rating, description, material, producingCountry, colors);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Case aCase = (Case) obj;
        return Objects.equals(id, aCase.id) &&
                Objects.equals(model, aCase.model) &&
                Objects.equals(brand, aCase.brand) &&
                Objects.equals(mainPictureUrl, aCase.mainPictureUrl) &&
                Objects.equals(price, aCase.price) &&
                Objects.equals(voteCount, aCase.voteCount) &&
                Objects.equals(rating, aCase.rating) &&
                Objects.equals(description, aCase.description) &&
                Objects.equals(material, aCase.material) &&
                Objects.equals(producingCountry, aCase.producingCountry) &&
                Objects.equals(colors, aCase.colors);
    }

    @Override
    public String toString() {
        return id + ") " + model + ", " + brand + ", " + price + ", rating " + rating + ", voteCount - " + voteCount
                + ", " + description + ", " + material + ", " + producingCountry + " colors: " + colors;
    }
}
