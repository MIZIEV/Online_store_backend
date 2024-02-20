package com.storeApp.service;

import com.storeApp.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void addNewCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    boolean deleteCategory(Long id);

    Category updateCategory(Category editedCategory, long id);
}