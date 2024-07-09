package com.storeApp.repository;

import com.storeApp.models.Comment;
import com.storeApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);

    List<Comment> findByAuthor(User user);
}