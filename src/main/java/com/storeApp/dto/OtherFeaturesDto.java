package com.storeApp.dto;

import com.storeApp.models.Phone;

public class OtherFeaturesDto {

    private Long id;

    private String feature;

    private Phone phone;

    public OtherFeaturesDto() {}

    public OtherFeaturesDto(Long id, String feature, Phone phone) {
        this.id = id;
        this.feature = feature;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

}
