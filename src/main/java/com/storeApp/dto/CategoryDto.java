package com.storeApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CategoryDto {

    private Long id;
    @NotNull(message = "The field `categoryName` mustn't be null!!!")
    @Size(min = 5, message = "The field must be longer than 5 characters!!!")
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoryDto categoryDto = (CategoryDto) obj;

        return Objects.equals(id, categoryDto.id) &&
                Objects.equals(categoryName, categoryDto.categoryName);
    }

    @Override
    public String toString() {
        return "id) " + id + " Category name - " + categoryName;
    }
}