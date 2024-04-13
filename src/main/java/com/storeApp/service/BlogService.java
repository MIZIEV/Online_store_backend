package com.storeApp.service;

import com.storeApp.models.Blog;

import java.util.List;

public interface BlogService {

    Blog saveNewBlog(Blog blog);

    List<Blog> getAllBlogs();

    void updateBlog(Blog blog);

    void deleteBlog(Blog blog);
}