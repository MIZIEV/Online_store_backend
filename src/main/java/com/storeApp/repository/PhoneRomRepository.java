package com.storeApp.repository;

import com.storeApp.models.PhoneRom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRomRepository extends JpaRepository<PhoneRom, Long> {

    Optional<PhoneRom> findPhoneRomById(long id);
}
