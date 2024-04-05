package com.storeApp.repository;

import com.storeApp.models.PhonePictureURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhonePictureURLRepository extends JpaRepository<PhonePictureURL, Long> {
    Optional<PhonePictureURL> findPhonePictureURLById(Long id);
}
