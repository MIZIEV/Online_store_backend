package com.storeApp.repository;

import com.storeApp.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long> {
    Optional<Color> findColorById(Long id);
}