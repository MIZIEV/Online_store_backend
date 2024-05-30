package com.storeApp.service;

import com.storeApp.models.Phone;
import com.storeApp.models.User;

import java.util.Set;

public interface UserService {

    User updateUserData(String email, User updatedUser);

    User getUserByPhoneNumber(String phoneNumber);

    Set<Phone> getWishListForUser(String username);
}