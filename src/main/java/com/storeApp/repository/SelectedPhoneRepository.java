package com.storeApp.repository;

import com.storeApp.models.phone.SelectedPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedPhoneRepository extends JpaRepository<SelectedPhone, Long> {

    void deleteAllByRomIdIn(List<Long> id);
}