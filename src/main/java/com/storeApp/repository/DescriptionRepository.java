package com.storeApp.repository;

import com.storeApp.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescriptionRepository  extends JpaRepository<Description, Long> {

    Optional<Description> findById(Long id);
}