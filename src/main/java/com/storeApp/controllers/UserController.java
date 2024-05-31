package com.storeApp.controllers;

import com.storeApp.dto.LoginDto;
import com.storeApp.dto.UserDto;
import com.storeApp.models.User;
import com.storeApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/{email}")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/data")
    @Transactional(readOnly = false)
    public ResponseEntity<?> updateUserData(@PathVariable("email") String email, @RequestBody UserDto updatedUser) {
        return new ResponseEntity<>(userService.updateUserData(email, convertToUser(updatedUser)), HttpStatus.OK);
    }

    @GetMapping("/wishList")
    public ResponseEntity<?> getWishListForUser(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getWishListForUser(email), HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changeUserPassword(@PathVariable("email") String email, @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.changeUserPassword(email, loginDto.getPassword()), HttpStatus.OK);
    }

    private User convertToUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }
}