package com.storeApp.service;

import com.storeApp.models.Category;
import com.storeApp.models.Product;

import java.util.List;

public interface ProductService {

    void addNewProduct(Product product);

    List<Product> getAllProducts(String sort, Long categoryId);


    List<Product> getAllProductsFilteredByCategory(Category category);

    Product getProductById(Long id);

    void deleteProduct(Long id);

    Product updateProduct(Product editedProduct, Long id);

    void putTheMark(Long id, Double mark);
}