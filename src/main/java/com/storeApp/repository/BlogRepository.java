package com.storeApp.repository;

import com.storeApp.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Optional<Blog> findBlogById(Long id);
}