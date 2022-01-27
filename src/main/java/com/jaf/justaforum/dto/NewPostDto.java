package com.jaf.justaforum.dto;

//klasa wykorzystywana do przyjmowania postów od użytkownika
public class NewPostDto {
    private final String title;
    private final String content;
    private final String postCategory;
    private final String username;

    //kojnstruktor klasy NewPostDto
    public NewPostDto(String title, String content, String postCategory, String username) {
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public String getUsername() {
        return username;
    }
}
