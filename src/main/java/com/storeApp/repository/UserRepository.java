package com.storeApp.repository;

import com.storeApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumberOrEmail(String phoneNumber, String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}