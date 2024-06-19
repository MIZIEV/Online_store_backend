package com.storeApp.repository;

import com.storeApp.models.PhonePictureUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhonePictureURLRepository extends JpaRepository<PhonePictureUrl, Long> {
    Optional<PhonePictureUrl> findPhonePictureURLById(Long id);
}
