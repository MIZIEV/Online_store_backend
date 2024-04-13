package com.storeApp.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class BlogDto {

    private Long id;

    private String blogPictureUrl;

    private String title;

    private String text;

    private LocalDateTime createdAt;

    public BlogDto(){}

    public BlogDto(Long id, String blogPictureUrl, String title, String text, LocalDateTime createdAt) {
        this.id = id;
        this.blogPictureUrl = blogPictureUrl;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogPictureUrl() {
        return blogPictureUrl;
    }

    public void setBlogPictureUrl(String blogPictureUrl) {
        this.blogPictureUrl = blogPictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogPictureUrl, title, text, createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlogDto blogDto = (BlogDto) obj;
        return Objects.equals(id, blogDto.id) &&
                Objects.equals(blogPictureUrl, blogDto.blogPictureUrl) &&
                Objects.equals(title, blogDto.title) &&
                Objects.equals(text, blogDto.text) &&
                Objects.equals(createdAt, blogDto.createdAt);
    }

    @Override
    public String toString() {
        return id + ") " + blogPictureUrl + " title -  " + title + ", " + text + " created at - " + createdAt.toString();
    }
}