package com.storeApp.repository;

import com.storeApp.models.Category;
import com.storeApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(Long id);

    List<Product> findByCategory(Category category);

    Optional<Product> findProductByModel(String model);

    List<Product> findByBrandAndModel(String brand, String model);
    List<Product> findByBrandOrModel(String brand, String model);
    List<Product> findByModelContainingIgnoreCase(String model);
}