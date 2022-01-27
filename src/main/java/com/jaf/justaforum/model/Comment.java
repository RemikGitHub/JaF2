package com.jaf.justaforum.model;

import java.time.LocalDateTime;

//model odzwierciedlający komentarz znajdujący się w bazie danych
public class Comment {
    private Long id;
    private String content;
    private LocalDateTime writeDateTime;
    private String username;
    private Long postsId;

    //konstruktor klasy Comment
    public Comment(Long id, String content, LocalDateTime writeDateTime, String username, Long postsId) {
        this.id = id;
        this.content = content;
        this.writeDateTime = writeDateTime;
        this.username = username;
        this.postsId = postsId;
    }
    
	 
	 //konstruktor klasy Comment bez podania id posta
    public Comment(String content, LocalDateTime writeDateTime, String username, Long postsId) {
        this.content = content;
        this.writeDateTime = writeDateTime;
        this.username = username;
        this.postsId = postsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getWriteDateTime() {
        return writeDateTime;
    }

    public void setWriteDateTime(LocalDateTime writeDateTime) {
        this.writeDateTime = writeDateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostsId() {
        return postsId;
    }

    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }
}
