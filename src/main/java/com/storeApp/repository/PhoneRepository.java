package com.storeApp.repository;

import com.storeApp.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findProductById(Long id);

    @Query("SELECT p FROM Phone p ORDER BY p.price")
    List<Phone> findAllProductsOrderedByPrice();

    @Query("SELECT p FROM Phone p ORDER BY p.price DESC")
    List<Phone> findAllProductsOrderedByPriceDesc();
}