package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.User;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {

        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            return userRepository.findByPhoneNumber(phoneNumber).get();
        } else {
            return null;
        }
    }

    @Override
    public Set<Phone> getWishListForUser(String username) {
        return userRepository.findByPhoneNumber(username).get().getWishList();
    }
}