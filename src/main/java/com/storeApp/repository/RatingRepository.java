package com.storeApp.repository;

import com.storeApp.models.Product;
import com.storeApp.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query("SELECT AVG(r.mark) FROM Rating r WHERE r.product =:product")
    Double findAverageMarkForProduct(@Param("product") Product product);
}