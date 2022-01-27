package com.jaf.justaforum.dto;

import com.jaf.justaforum.model.PostCategory;

//klasa wykorzystywana do wyświetlania postów
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private PostCategory postCategory;
    private String publishedDateTime;
    private String username;

    //konstruktor klasy PostDto
    public PostDto(Long id, String title, String content, PostCategory postCategory, String publishedDateTime, String username) {
        this.id = id;
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

    public String getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(String publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
