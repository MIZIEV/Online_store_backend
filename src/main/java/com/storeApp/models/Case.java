package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "case_entity")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private Brand brand;
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
    @JsonManagedReference
    @JoinTable(name = "case_colors",
    joinColumns = @JoinColumn(name = "case_id"),
    inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors = new HashSet<>();


    public Case(){};

    public Case(Long id, Brand brand, Double price, Long voteCount, Double rating, String description,
                String material, String producingCountry) {
        this.id = id;
        this.brand = brand;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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
}
