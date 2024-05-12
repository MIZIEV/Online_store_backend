package com.storeApp.controllers;


import com.storeApp.models.Comment;
import com.storeApp.models.Phone;
import com.storeApp.models.User;
import com.storeApp.repository.UserRepository;
import com.storeApp.service.CommentService;
import com.storeApp.service.PhoneService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/phone/{phoneId}/comment")
public class CommentController {

    private final PhoneService phoneService;
    private final CommentService commentService;
    private final UserRepository userRepository;

    @Autowired
    public CommentController(PhoneService phoneService, CommentService commentService,
                             UserRepository userRepository) {
        this.phoneService = phoneService;
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewComment(@PathVariable("phoneId") Long phoneId,
                                           @RequestBody Comment comment) {

        String usernameOrEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new OnlineStoreApiException(HttpStatus.BAD_REQUEST, "User not found"));

        return new ResponseEntity<>(commentService.addComment(user, phoneId, comment), HttpStatus.CREATED);
    }
}
