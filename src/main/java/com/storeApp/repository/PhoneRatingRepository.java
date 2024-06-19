package com.storeApp.repository;

import com.storeApp.models.PhoneRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRatingRepository extends JpaRepository<PhoneRating, Long> {
}