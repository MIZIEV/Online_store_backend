package com.storeApp.dto;

import com.storeApp.models.Phone;

public class ProductCharacteristicDto {

    private Long id;
    private String characteristicName;
    private String characteristicValue;
    private Phone phone;

    public ProductCharacteristicDto(){}

    public ProductCharacteristicDto(Long id, String characteristicName, String characteristicValue, Phone phone) {
        this.id = id;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
        this.phone = phone;
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

    public Phone getProduct() {
        return phone;
    }

    public void setProduct(Phone phone) {
        this.phone = phone;
    }
}