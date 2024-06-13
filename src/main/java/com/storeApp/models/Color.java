package com.storeApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storeApp.models.phone.Phone;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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
    @JsonIgnore
    private List<Phone> phones;
    @ManyToMany(mappedBy = "colors")
    @JsonIgnore
    private List<Case> cases;

    public Color() {
    }

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phoneList) {
        this.phones = phoneList;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> caseList) {
        this.cases = caseList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, colorName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Color color = (Color) obj;
        return Objects.equals(id, color.id) &&
                Objects.equals(colorName, color.colorName);
    }

    @Override
    public String toString() {
        return id + ") " + colorName;
    }
}
