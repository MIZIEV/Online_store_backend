package com.storeApp.dto;

import com.storeApp.models.Product;

public class ProductCharacteristicDto {

    private Long id;
    private String characteristicName;
    private String characteristicValue;
    private Product product;

    public ProductCharacteristicDto(){}

    public ProductCharacteristicDto(Long id, String characteristicName, String characteristicValue, Product product) {
        this.id = id;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
        this.product = product;
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
}