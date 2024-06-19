package com.storeApp.dto;

import com.storeApp.models.phone.Phone;

public class PhonePictureUrlDto {

    private Long id;
    private String url;
    private Phone phone;

    public PhonePictureUrlDto() {}

    public PhonePictureUrlDto(Long id, String url, Phone phone) {
        this.id = id;
        this.url = url;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}