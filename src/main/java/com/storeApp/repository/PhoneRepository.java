package com.storeApp.repository;

import com.storeApp.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findPhoneById(Long id);

    @Query("SELECT p FROM Phone p ORDER BY p.price")
    List<Phone> findAllProductsOrderedByPrice();

    @Query("SELECT p FROM Phone p ORDER BY p.price DESC")
    List<Phone> findAllProductsOrderedByPriceDesc();

    @Query("SELECT DISTINCT p.screenSize FROM Phone p")
    Set<Double> findDistinctScreenSize();

    @Query("SELECT DISTINCT p.resolution FROM Phone p")
    Set<String> findDistinctResolution();

    @Query("SELECT DISTINCT p.processor FROM Phone p")
    Set<String> findDistinctProcessor();

    @Query("SELECT DISTINCT p.countOfCores FROM Phone p")
    Set<Byte> findDistinctCountOfCores();

    @Query("SELECT DISTINCT p.ram FROM Phone p")
    Set<Short> findDistinctRam();

    @Query("SELECT DISTINCT p.batteryCapacity FROM Phone p")
    Set<Short> findDistinctBatteryCapacity();

    @Query("SELECT DISTINCT p.countOfSimCard FROM Phone p")
    Set<Byte> findDistinctCountOfSimCard();

}