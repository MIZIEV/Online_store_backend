package com.storeApp.repository;

import com.storeApp.models.MobileCommunicationStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileCommunicationStandardRepository extends JpaRepository<MobileCommunicationStandard, Long> {
}