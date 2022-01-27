package com.jaf.justaforum.util;

import com.jaf.justaforum.dto.PostDto;
import com.jaf.justaforum.model.Post;

import java.time.format.DateTimeFormatter;

public class PostConverter {
    //formater daty na format "dd MMMM yyyy, HH:mm:ss"
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
    
	 //prywatny konstruktor, aby nie tworzyć instancji klasy
	 private PostConverter() {}

    //konwertuje obiekt klasy Post na obiekt klasy PostDto ze skróconą treścią do 350 znaków
    public static PostDto createShortPostDto(Post source) {
        int charsInContent = 350;

        String shortContent = source.getContent();
        if (shortContent.length() > charsInContent) {
            shortContent = shortContent.substring(0, charsInContent) + "...";
        }

        return new PostDto(
                source.getId(),
                source.getTitle(),
                shortContent,
                source.getPostCategory(),
                source.getPublishedDateTime().format(formatter),
                source.getUsername()
        );
    }
	     //konwertuje obiekt klasy Post na obiekt klasy PostDto
        public static PostDto createPostDto(Post source){
        return new PostDto(
                    source.getId(),
                    source.getTitle(),
                    source.getContent(),
                    source.getPostCategory(),
                    source.getPublishedDateTime().format(formatter),
                    source.getUsername()
            );
    }
}
