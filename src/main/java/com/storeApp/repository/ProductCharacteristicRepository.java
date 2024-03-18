package com.storeApp.repository;

import com.storeApp.models.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {

    Optional<ProductCharacteristic> findProductCharacteristicById(Long id);
}