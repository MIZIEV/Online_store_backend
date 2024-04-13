package com.storeApp.service;

import com.storeApp.models.Blog;

import java.util.List;

public interface BlogService {

    Blog saveNewBlog(Blog blog);

    List<Blog> getAllBlogs();

    void updateBlog(Blog editedBlog, Long id);

    void deleteBlog(Long id);
}