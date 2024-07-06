package com.storeApp.repository;

import com.storeApp.models.MobileCommunicationStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MobileCommunicationStandardRepository extends JpaRepository<MobileCommunicationStandard, Long> {

    @Modifying
    @Transactional
    void deleteAllByPhoneId(Long id);
}