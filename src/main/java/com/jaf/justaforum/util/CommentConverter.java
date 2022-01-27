package com.jaf.justaforum.util;

import com.jaf.justaforum.dto.CommentDto;
import com.jaf.justaforum.dto.PostDto;
import com.jaf.justaforum.model.Comment;
import com.jaf.justaforum.model.Post;

import java.time.format.DateTimeFormatter;

public class CommentConverter {
	 //formater daty na "dd MMMM yyyy, HH:mm:ss"
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");

	 //prywatny konstruktor, aby nie tworzyć instancji klasy
    private CommentConverter() {
    }

    //konwerter zmieniający klasę Comment na CommentDto (z obiektu zwracanego przez bazę danych na obiekt wyświetlany dla użytkownika)
    public static CommentDto createCommentDto(Comment source) {
        return new CommentDto(
                source.getId(),
                source.getContent(),
                source.getWriteDateTime().format(formatter),
                source.getUsername()
        );
    }
}
