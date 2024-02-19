package com.storeApp.controllers;

import com.storeApp.models.Category;
import com.storeApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addNewCategory(@RequestBody Category category){

        categoryService.addNewCategory(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
}