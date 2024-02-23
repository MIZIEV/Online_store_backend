package com.storeApp.service.implementation;

import com.storeApp.dto.LoginDto;
import com.storeApp.dto.RegisterDto;
import com.storeApp.models.Role;
import com.storeApp.models.User;
import com.storeApp.repository.RoleRepository;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged -in successfully!!!";
    }
}
