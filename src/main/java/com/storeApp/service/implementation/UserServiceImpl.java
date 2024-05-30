package com.storeApp.service.implementation;

import com.storeApp.models.Phone;
import com.storeApp.models.User;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.UserService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public User updateUserData(String email, User updatedUser) {
        User currentUser = null;

        if (userRepository.findByEmail(email).isPresent()) {
            currentUser = userRepository.findByEmail(email).get();

            currentUser.setFirstName(updatedUser.getFirstName());
            currentUser.setLastName(updatedUser.getLastName());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

            userRepository.save(currentUser);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "User not found");
        }

        return currentUser;
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
    public Set<Phone> getWishListForUser(String email) {
        return userRepository.findByEmail(email).get().getWishList();
    }
}