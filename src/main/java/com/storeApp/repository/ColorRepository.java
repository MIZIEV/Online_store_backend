package com.storeApp.repository;

import com.storeApp.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findColorById(Long id);

    @Query("SELECT c FROM Color c WHERE c.id IN:colorIds")
    Set<Color> findByColorsIds(@Param("colorIds") List<Long> colorIds);
}