package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "modile_communication_standard")
public class MobileCommunicationStandard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "standard_name")
    private String standardName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phone_id",referencedColumnName = "id")
    private Phone phone;
    public MobileCommunicationStandard(){}

    public MobileCommunicationStandard(Long id, String standardName, Phone phone) {
        this.id = id;
        this.standardName = standardName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}