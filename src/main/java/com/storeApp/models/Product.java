package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "description")
    private String description;
    @Column(name = "picture_URL")
    private String pictureURL;
    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private List<ProductCharacteristic> characteristicList;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> rating;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    public Product() {
    }

    public Product(Long id, String brand, String model, String description, String pictureURL, Double price) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public List<ProductCharacteristic> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(List<ProductCharacteristic> characteristicList) {
        this.characteristicList = characteristicList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, description, pictureURL, price, category);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;

        return Objects.equals(id, product.id) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(description, product.description) &&
                Objects.equals(pictureURL, product.pictureURL) &&
                Objects.equals(price, product.price) &&
                Objects.equals(category, product.category);
    }

    @Override
    public String toString() {
        return id + ") " + brand + ", " + model + ", " + description + ", " + pictureURL + "\n " + " price - " + price;
    }
}