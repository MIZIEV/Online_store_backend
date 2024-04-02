package com.storeApp.dto;

import com.storeApp.models.Case;
import com.storeApp.models.Phone;

import java.util.List;

public class ColorDto {

    private Long id;

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
}
