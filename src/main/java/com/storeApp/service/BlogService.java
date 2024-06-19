package com.storeApp.service;

import com.storeApp.models.Blog;

import java.util.List;

public interface BlogService {

    Blog saveNewBlog(Blog blog);

    List<Blog> getAllBlogs();

    Blog getOneBlog(Long id);

    Blog updateBlog(Blog editedBlog, Long id);

    void deleteBlog(Long id);
}