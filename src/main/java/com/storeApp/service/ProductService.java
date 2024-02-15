package com.storeApp.service;

import com.storeApp.models.Product;

import java.util.List;

public interface ProductService {

    void addNewProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);

    void updateProduct(Product editedProduct, Long id);
}
