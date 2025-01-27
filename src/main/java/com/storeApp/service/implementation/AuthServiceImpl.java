package com.storeApp.service.implementation;

import com.storeApp.dto.JwtAuthResponse;
import com.storeApp.dto.LoginDto;
import com.storeApp.dto.RegisterDto;
import com.storeApp.models.Role;
import com.storeApp.models.User;
import com.storeApp.repository.RoleRepository;
import com.storeApp.repository.UserRepository;
import com.storeApp.security.CustomUserDetailService;
import com.storeApp.security.JwtTokenProvider;
import com.storeApp.service.AuthService;
import com.storeApp.util.exception.IncorrectUsernameOrPasswordException;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {

            throw new OnlineStoreApiException(HttpStatus.BAD_REQUEST, "Email already exists!!!");
        }

        User user = new User();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhoneNumber(registerDto.getPhoneNumber());
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
    public JwtAuthResponse login(LoginDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            Optional<User> userOptional = userRepository.findByPhoneNumberOrEmail(loginDto.getEmail(),
                    loginDto.getEmail());

            String role = null;

            if (userOptional.isPresent()) {
                User loggedUser = userOptional.get();
                Optional<Role> optionalRole = loggedUser.getRole().stream().findFirst();
                if (optionalRole.isPresent()) {
                    Role userRole = optionalRole.get();
                    role = userRole.getName();
                }
            }

            User user = userRepository.findByPhoneNumberOrEmail(loginDto.getEmail(),
                    loginDto.getEmail()).get();

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setRole(role);
            jwtAuthResponse.setAccessToken(token);
            jwtAuthResponse.setRefreshToken(refreshToken);
            jwtAuthResponse.setFirstName(user.getFirstName());
            jwtAuthResponse.setLastName(user.getLastName());
            jwtAuthResponse.setPhoneNumber(user.getPhoneNumber());
            jwtAuthResponse.setEmail(user.getEmail());

            return jwtAuthResponse;

        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new IncorrectUsernameOrPasswordException(HttpStatus.NOT_FOUND, "Incorrect username or password");
            }
        }
        return null;
    }
}