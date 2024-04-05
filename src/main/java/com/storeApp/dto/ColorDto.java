package com.storeApp.dto;

import com.storeApp.models.Case;
import com.storeApp.models.Phone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class ColorDto {

    private Long id;

    @NotNull(message = "The field `colorName` mustn't be null!")
    @Size(min = 3, message = "The field `colorName` must be longer than 3 characters!")
    private String colorName;

    private List<Phone> phoneList;
    private List<Case> caseList;

    public ColorDto() {}

    public ColorDto(Long id, String colorName) {
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

    @Override
    public int hashCode() {
        return Objects.hash(id, colorName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ColorDto colorDto = (ColorDto) obj;
        return Objects.equals(id, colorDto.id) &&
                Objects.equals(colorName, colorDto.colorName);
    }

    @Override
    public String toString() {
        return id + ") " + colorName;
    }
}