package com.jaf.justaforum.dao;

import com.jaf.justaforum.model.Comment;
import com.jaf.justaforum.model.Post;
import com.jaf.justaforum.model.PostCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDao extends BaseDao {

    //zapisuje komentarz do bazy danych
    public void saveComment(Comment comment) {
        final String query = """
                INSERT INTO
                    comments (comment, write_date_time, users_username, posts_id)
                VALUES
                    (?, ?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, comment.getContent());
            statement.setObject(2, comment.getWriteDateTime());
            statement.setString(3, comment.getUsername());
            statement.setLong(4, comment.getPostsId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca listę komentarzy posta o podanym id, lista jest posortowana według daty napisania malejąco
    public List<Comment> findByPostIdOrderByWriteDateTimeDesc(Long id) {
        List<Comment> comments = new ArrayList<>();
        final String query = """
                SELECT
                    comments.id, comments.comment, comments.write_date_time, comments.users_username, comments.posts_id
                FROM
                    comments
                WHERE
                    comments.posts_id = ?
                ORDER BY comments.write_date_time DESC
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(mapRow(resultSet));
            }
            return comments;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca komentarz o podanym id
    public Optional<Comment> findById(Long id) {
        final String query = """
                SELECT
                    comments.id, comments.comment, comments.write_date_time, comments.users_username, comments.posts_id
                FROM
                    comments
                WHERE
                    comments.id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //usuwa komentarz o podanym id
    public void deleteCommentById(Long id) {
        final String query = """
                DELETE FROM
                    comments
                WHERE
                    comments.id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //mapper konwertująćy obiekt klasy ResultSet na obiekt klasy Comment
    private Comment mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String content = resultSet.getString("comment");
        LocalDateTime writeDateTime = resultSet.getObject("write_date_time", LocalDateTime.class);
        String username = resultSet.getString("users_username");
        Long postsId = resultSet.getLong("posts_id");

        return new Comment(id, content, writeDateTime, username, postsId);
    }
}
