package com.storeApp.repository;

import com.storeApp.models.PhoneRating;
import com.storeApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRatingRepository extends JpaRepository<PhoneRating, Long> {
    List<PhoneRating> findByUser(User user);
}