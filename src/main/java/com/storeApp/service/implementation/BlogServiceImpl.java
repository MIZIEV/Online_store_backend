package com.storeApp.service.implementation;

import com.storeApp.models.Blog;
import com.storeApp.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog saveNewBlog(Blog blog) {
        return null;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return List.of();
    }

    @Override
    public void updateBlog(Blog blog) {

    }

    @Override
    public void deleteBlog(Blog blog) {

    }
}
