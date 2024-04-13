package com.storeApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_url")
    private String blogPictureUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Blog() {}

    public Blog(Long id, String blogPictureUrl, String title, String text, LocalDateTime createdAt) {
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
        Blog blog = (Blog) obj;
        return Objects.equals(id, blog.id) &&
                Objects.equals(blogPictureUrl, blog.blogPictureUrl) &&
                Objects.equals(title, blog.title) &&
                Objects.equals(text, blog.text) &&
                Objects.equals(createdAt, blog.createdAt);
    }

    @Override
    public String toString() {
        return id + ") " + blogPictureUrl + " title -  " + title + ", " + text + " created at - " + createdAt.toString();
    }
}