package com.storeApp.dto;

import com.storeApp.models.Phone;
import com.storeApp.models.User;

public class PhoneRatingDto {

    private Long id;

    private Double rating;

    private Phone phone;

    private User user;

    public PhoneRatingDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}