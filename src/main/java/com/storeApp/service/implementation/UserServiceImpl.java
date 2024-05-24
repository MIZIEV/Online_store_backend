package com.storeApp.service.implementation;

import com.storeApp.models.User;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {

        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        } else {
            return null;
        }
    }
}