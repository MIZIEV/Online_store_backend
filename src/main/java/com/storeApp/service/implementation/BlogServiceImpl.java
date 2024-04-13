package com.storeApp.service.implementation;

import com.storeApp.models.Blog;
import com.storeApp.repository.BlogRepository;
import com.storeApp.service.BlogService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Blog saveNewBlog(Blog blog) {
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBlog(Blog editedBlog, Long id) {

        Blog blogForUpdating = null;

        if (blogRepository.findBlogById(id).isPresent()) {
            blogForUpdating = blogRepository.findById(id).get();

            blogForUpdating.setId(id);
            blogForUpdating.setTitle(editedBlog.getTitle());
            blogForUpdating.setBlogPictureUrl(editedBlog.getBlogPictureUrl());
            blogForUpdating.setText(editedBlog.getText());

            blogRepository.save(blogForUpdating);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Blog with id - " + id + " not found");
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBlog(Long id) {
        if (blogRepository.findBlogById(id).isPresent()) {
            blogRepository.delete(blogRepository.findBlogById(id).get());
        }
    }
}