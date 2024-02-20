package com.storeApp.service.implementation;

import com.storeApp.models.Category;
import com.storeApp.repository.CategoryRepository;
import com.storeApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Category updateCategory(Category editedCategory, long id) {

        Category categoryForUpdating = null;

        if (categoryRepository.findCategoryById(id).isPresent()) {

            categoryForUpdating = categoryRepository.findCategoryById(id).get();
            categoryForUpdating.setCategoryName(editedCategory.getCategoryName());
            categoryRepository.save(categoryForUpdating);
            return categoryForUpdating;

        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCategory(Long id) {
        categoryRepository.delete(getCategoryById(id).get());
    }
}