package com.storeApp.service.implementation;

import com.storeApp.models.Category;
import com.storeApp.repository.CategoryRepository;
import com.storeApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }
}