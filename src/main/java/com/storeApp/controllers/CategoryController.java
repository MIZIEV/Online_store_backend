package com.storeApp.controllers;

import com.storeApp.dto.CategoryDto;
import com.storeApp.models.Category;
import com.storeApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error:\n");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {

            Category category = convertToCategory(categoryDto);

            categoryService.addNewCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable long id) {

        Optional<Category> category = categoryService.getCategoryById(id);

        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else {
            String message = "Error: " + "Category with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) {

        Optional<Category> category = categoryService.getCategoryById(id);

        if (category.isPresent()) {
            return new ResponseEntity<>(categoryService.updateCategory(convertToCategory(categoryDto), id), HttpStatus.OK);
        } else {
            String message = "Error: " + "Category with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            String message = "Error: " + "Category with id - " + id + " not found!!!\n" +
                    "Timestamp: " + LocalDateTime.now();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    private Category convertToCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());

        return category;
    }
}