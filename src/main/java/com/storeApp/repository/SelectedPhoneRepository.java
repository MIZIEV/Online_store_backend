package com.storeApp.repository;

import com.storeApp.models.phone.SelectedPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedPhoneRepository extends JpaRepository<SelectedPhone, Long> {
}