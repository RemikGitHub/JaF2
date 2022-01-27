package com.jaf.justaforum.service;

import com.jaf.justaforum.dao.CommentDao;
import com.jaf.justaforum.dto.CommentDto;
import com.jaf.justaforum.dto.NewCommentDto;
import com.jaf.justaforum.model.Comment;
import com.jaf.justaforum.util.CommentConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private final CommentDao commentDao = new CommentDao();

    //dodaje komentarz do bazy danych
    public void addNewComment(NewCommentDto newCommentDto) {
        Comment commentToSave = CommentService.CommentMapper.map(newCommentDto);

        commentDao.saveComment(commentToSave);
    }

    //metoda przyjmuje id posta po czym zwraca listę jego komentarzy
    public List<CommentDto> getPostComments(Long id) {
        List<Comment> result = commentDao.findByPostIdOrderByWriteDateTimeDesc(id);
        return result.stream().map(CommentConverter::createCommentDto).collect(Collectors.toList());
    }

    //przyjmuje id komentarza po czym go usuwa z bazy danych
    public void delCommentById(Long id) {
        commentDao.deleteCommentById(id);
    }

    //mapper pozwalający skonwertować obiekt klasy NewCommentDto na obiekt klasy Comment
    private static class CommentMapper {
        static Comment map(NewCommentDto newCommentDto) {
            return new Comment(
                    newCommentDto.getContent(),
                    LocalDateTime.now(),
                    newCommentDto.getUsername(),
                    newCommentDto.getPostsId()
            );
        }
    }
}
