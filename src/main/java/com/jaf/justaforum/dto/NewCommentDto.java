package com.jaf.justaforum.dto;

//klasa wykorzystywana do przyjmowania komentarzy od użytkownika
public class NewCommentDto {
    private String content;
    private String username;
    private Long postsId;

    //konstruktor klasy NewCommentDto
    public NewCommentDto(String content, String username, Long postsId) {
        this.content = content;
        this.username = username;
        this.postsId = postsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
