package com.storeApp.dto;

public class DescriptionDto {

    private Long id;
    private String descriptionText;

    public DescriptionDto(){}

    public DescriptionDto(Long id, String descriptionText) {
        this.id = id;
        this.descriptionText = descriptionText;
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
}