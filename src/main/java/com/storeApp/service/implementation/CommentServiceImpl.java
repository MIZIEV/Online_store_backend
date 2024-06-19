package com.storeApp.service.implementation;

import com.storeApp.models.Comment;
import com.storeApp.models.User;
import com.storeApp.repository.CommentRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.CommentService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PhoneRepository phoneRepository) {
        this.commentRepository = commentRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Comment addComment(User author, Long phoneId, Comment comment) {

        Comment newComment = comment;
        newComment.setAuthorEmail(author.getEmail());
        comment.setAuthor(author);
        comment.setPhone(phoneRepository.findPhoneById(phoneId).get());
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setAuthorName(author.getFirstName());
        commentRepository.save(newComment);

        return newComment;
    }

    @Override
    public List<Comment> getAllComments(Long phoneId) {

        if (phoneRepository.findPhoneById(phoneId).isPresent()) {
            return phoneRepository.findPhoneById(phoneId).get().getComments();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Phone with id - " + phoneId + " not found");
        }
    }

    @Override
    public void deleteComment(Long id) {
        if (commentRepository.findCommentById(id).isPresent()) {
            commentRepository.delete(commentRepository.findCommentById(id).get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Comment with id - " + id + " not found");
        }
    }
}