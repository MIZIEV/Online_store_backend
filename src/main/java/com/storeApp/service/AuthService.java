package com.storeApp.service;

import com.storeApp.dto.JwtAuthResponse;
import com.storeApp.dto.LoginDto;
import com.storeApp.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
