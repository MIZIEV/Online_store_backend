package com.storeApp.service;

import com.storeApp.models.Category;

import java.util.List;

public interface CategoryService {

    void addNewCategory(Category category);

    List<Category> getAllCategories();
}
