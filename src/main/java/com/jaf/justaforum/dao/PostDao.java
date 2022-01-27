package com.jaf.justaforum.dao;

import com.jaf.justaforum.model.Post;
import com.jaf.justaforum.model.PostCategory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDao extends BaseDao {

    //zwraca listę postów o podanej kategorii posortowaną malejąco według daty
    public List<Post> findByPostCategoryOrderByPublishedDateTimeDesc(PostCategory postCategory) {
        List<Post> posts = new ArrayList<>();
        final String query = """
                SELECT
                    posts.id, posts.title, posts.content, posts.post_category, posts.published_date_time, posts.user_username
                FROM
                    posts
                WHERE
                    posts.post_category = ?
                ORDER BY posts.published_date_time DESC
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, postCategory.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(mapRow(resultSet));
            }
            return posts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca listę postów danego użytkownika
    public List<Post> findByUsernameOrderByPublishedDateTimeDesc(String username) {
        List<Post> posts = new ArrayList<>();
        final String query = """
                SELECT
                    posts.id, posts.title, posts.content, posts.post_category, posts.published_date_time, posts.user_username
                FROM
                    posts
                WHERE
                    posts.user_username = ?
                ORDER BY posts.published_date_time DESC
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(mapRow(resultSet));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca liczbę postów danego użytkownika
    public Long countByUsername(String username) {
        final String query = """
                SELECT
                    count(posts.id)
                FROM
                    posts
                WHERE
                posts.user_username = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca post o podanym id z bazy danych
    public Optional<Post> findById(Long id) {
        final String query = """
                SELECT
                    posts.id, posts.title, posts.content, posts.post_category, posts.published_date_time, posts.user_username
                FROM
                    posts
                WHERE
                    posts.id = ?
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

    //zapisuje post do bazy danych
    public void savePost(Post post) {
        final String query = """
                INSERT INTO
                    posts (title, content, post_category, published_date_time, user_username)
                VALUES
                    (?, ?, ?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getPostCategory().toString());
            statement.setObject(4, post.getPublishedDateTime());
            statement.setObject(5, post.getUsername());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //usuwa post z bazy danych po podaniu jego id
    public void deletePostById(Long id) {
        final String query = """
                DELETE FROM
                    posts
                WHERE
                    id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //mapper konwertujący obiekt klasy ResultSet na obiekt klasy Post
    private Post mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        PostCategory postCategory = PostCategory.valueOf(resultSet.getString("post_category"));
        LocalDateTime publishedDateTime = resultSet.getObject("published_date_time", LocalDateTime.class);
        String username = resultSet.getString("user_username");

        return new Post(id, title, content, postCategory, publishedDateTime, username);
    }
}
