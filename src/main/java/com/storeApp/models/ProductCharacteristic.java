package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_characteristic")
public class ProductCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "characteristic_name")
    private String characteristicName;
    @Column(name = "characteristic_value")
    private String characteristicValue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public ProductCharacteristic() {}

    public ProductCharacteristic(Long id, String characteristicName, String characteristicValue) {
        this.id = id;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(String characteristicValue) {
        this.characteristicValue = characteristicValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() { return Objects.hash(id, characteristicName, characteristicValue); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductCharacteristic productCharacteristic = (ProductCharacteristic) obj;

        return Objects.equals(id, productCharacteristic.id) &&
                Objects.equals(characteristicName, productCharacteristic.characteristicName) &&
                Objects.equals(characteristicValue, productCharacteristic.characteristicValue);
    }
    @Override
    public String toString() {
        return id + ") " + characteristicName + ", " + characteristicValue;
    }
}