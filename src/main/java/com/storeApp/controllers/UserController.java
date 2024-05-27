package com.storeApp.controllers;

import com.storeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/{username}")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wishList")
    public ResponseEntity<?> getWishListForUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getWishListForUser(username), HttpStatus.OK);
    }
}