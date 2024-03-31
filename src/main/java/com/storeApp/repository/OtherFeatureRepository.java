package com.storeApp.repository;

import com.storeApp.models.OtherFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtherFeatureRepository extends JpaRepository<OtherFeatures, Long> {

    Optional<OtherFeatures> findProductCharacteristicById(Long id);
}