package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.storeApp.models.phone.Phone;
import jakarta.persistence.*;

@Entity
@Table(name = "picture_url")
public class PhonePictureUrl {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "picture_url")
    private String url;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private Phone phone;

    public PhonePictureUrl(){}

    public PhonePictureUrl(Long id, String url, Phone phone) {
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