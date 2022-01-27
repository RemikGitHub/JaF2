package com.jaf.justaforum.dao;

import com.jaf.justaforum.model.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TokenDao extends BaseDao {

    //zapisuje token do bazy danych
    public void saveToken(Token token) {
        final String query = """
                INSERT INTO
                    tokens (token, created_date, users_id)
                VALUES
                    (?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token.getToken());
            statement.setObject(2, token.getCreatedDate());
            statement.setLong(3, token.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //usuwa token z bazy danych
    public void deleteToken(Long id) {
        final String query = """
                DELETE FROM
                    tokens
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

    //zwraca token po jego nazwie
    public Optional<Token> findByToken(String token) {
        final String query = """
                SELECT
                    id, token, created_date, users_id
                FROM
                    tokens
                WHERE
                    token = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
	 //mapper konwertujący obiekt klasy ResultSet na obiekt klasy Token
    private Token mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String token = resultSet.getString("token");
        LocalDateTime createdDate = resultSet.getObject("created_date", LocalDateTime.class);
        Long userId = resultSet.getLong("users_id");

        return new Token(id, token, createdDate, userId);
    }
}
