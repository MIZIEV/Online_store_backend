package com.storeApp.service.implementation;

import com.storeApp.dto.RegisterDto;
import com.storeApp.models.Role;
import com.storeApp.models.User;
import com.storeApp.repository.RoleRepository;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            //todo create exception !!!!!

            throw null;
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw null;
        }

        User user = new User();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findByName("ROLE_USER"));
        user.setRole(role);

        userRepository.save(user);
        return "User registered successfully!!!";
    }
}
