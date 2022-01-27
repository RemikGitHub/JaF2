package com.jaf.justaforum.model;

import java.time.LocalDateTime;

//model odzwierciedlający post znajdujący się w bazie danych
public class Post {
    private Long id;
    private String title;
    private String content;
    private PostCategory postCategory;
    private LocalDateTime publishedDateTime;
    private String username;

    //konstruktor klasy Post
    public Post(Long id, String title, String content, PostCategory postCategory, LocalDateTime publishedDateTime, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
        this.publishedDateTime = publishedDateTime;
        this.username = username;
    }
	 
	 //konstruktor klasy Post bez id posta
    public Post(String title, String content, PostCategory postCategory, LocalDateTime publishedDateTime, String username) {
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
        this.publishedDateTime = publishedDateTime;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(LocalDateTime publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
