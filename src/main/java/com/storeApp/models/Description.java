package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.storeApp.models.phone.Phone;
import jakarta.persistence.*;

@Entity
@Table(name = "description")
public class Description {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_text")
    private String descriptionText;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phone_id",referencedColumnName = "id")
    private Phone phone;

    public Description(){}

    public Description(String descriptionText, Long id) {
        this.descriptionText = descriptionText;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
