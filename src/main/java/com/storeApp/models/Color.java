package com.storeApp.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "color")
public class Color {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "color_name")
    private String colorName;

    @ManyToMany(mappedBy = "colors")
    private List<Phone> phoneList;
    @ManyToMany(mappedBy = "colors")
    private List<Case> caseList;

    public Color(){}

    public Color(Long id, String colorName) {
        this.id = id;
        this.colorName = colorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public List<Case> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<Case> caseList) {
        this.caseList = caseList;
    }
}