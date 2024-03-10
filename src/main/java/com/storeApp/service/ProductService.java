package com.storeApp.service;

import com.storeApp.models.Category;
import com.storeApp.models.Product;

import java.util.List;

public interface ProductService {

    void addNewProduct(Product product);

    List<Product> getAllProducts(String sort, Long categoryId);

    List<Product> getAllProducts();

    List<Product> getAllProductsFilteredByCategory(Category category);

    Product getProductById(Long id);

    void deleteProduct(Long id);

    Product updateProduct(Product editedProduct, Long id);

    Product getProductByModel(String mode);

    List<Product> getProductsByBrandAndModel(String brand, String model);

    List<Product> getProductsByBrandOrModel(String brand, String model);
    List<Product> getProductsByModelContainingIgnoreCase(String model);
}