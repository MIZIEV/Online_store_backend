package com.storeApp.service;

import com.storeApp.models.Comment;
import com.storeApp.models.User;

import java.util.List;

public interface CommentService {

    Comment addComment(User author, Long phoneId, Comment comment);

    List<Comment> getAllComments(Long phoneId);

    void deleteComment(Long id);
}