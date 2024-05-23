package com.storeApp.service;

import com.storeApp.models.User;

public interface UserService {
    User getUserByUsername(String username);
}