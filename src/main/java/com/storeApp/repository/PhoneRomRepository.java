package com.storeApp.repository;

import com.storeApp.models.PhoneRom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PhoneRomRepository extends JpaRepository<PhoneRom, Long> {

    Optional<PhoneRom> findPhoneRomById(long id);

    @Modifying
    @Transactional
    void deleteAllByPhoneId(Long id);

    @Query("SELECT DISTINCT p.romSize FROM PhoneRom p")
    Set<Short> findDistinctPhoneRom();
}