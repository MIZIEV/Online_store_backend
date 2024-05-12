package com.storeApp.controllers;


import com.storeApp.dto.BlogDto;
import com.storeApp.models.Blog;
import com.storeApp.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveNewBlog(@RequestBody BlogDto blogDto) {
        return new ResponseEntity<>(blogService.saveNewBlog(convertToBlog(blogDto)), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBlog(@PathVariable("id") Long id) {
        return new ResponseEntity<>(blogService.getOneBlog(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody BlogDto editedBlogDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(blogService.updateBlog(convertToBlog(editedBlogDto), id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Blog convertToBlog(BlogDto blogDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(blogDto, Blog.class);
    }
}