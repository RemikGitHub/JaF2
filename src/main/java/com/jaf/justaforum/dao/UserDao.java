package com.jaf.justaforum.dao;

import com.jaf.justaforum.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserDao extends BaseDao {

    //zapisuje użytkownika oraz jego role w bazie danych
    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }
	 
	 //zapisuje użytkownika w bazie danych
    private void saveUser(User user) {
        final String query = """
                INSERT INTO
                    users (email, username,  password, registration_date_time, active)
                VALUES
                    (?, ?, ?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setObject(4, user.getRegistrationDate());
            statement.setObject(5, user.getActive());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zapisuje role użytkownika w bazie danych
    private void saveUserRole(User user) {
        final String query = """
                INSERT INTO
                    user_role (username)
                VALUES
                    (?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	 
    //zwraca użytkownika o podanej nazwie
    public Optional<User> findByUsername(String username) {
        final String query = """
                SELECT
                    id, username, email, password, registration_date_time, active
                FROM
                    users
                WHERE
                    username = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca użytkownika z bazy danych o podanym adresie email
    public Optional<User> findByEmail(String email) {
        final String query = """
                SELECT
                    id, username, email, password, registration_date_time, active
                FROM
                    users
                WHERE
                    email = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //zwraca użytkownika o podanym id
    public Optional<User> findById(Long id) {
        final String query = """
                SELECT
                    id, username, email, password, registration_date_time, active
                FROM
                    users
                WHERE
                    id = ?
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

    //modyfikuje pole "active" użytkownikowi o podanym id
    public void updateActive(Long id, Short active) {
        final String query = """
                UPDATE
                    users
                SET
                    active = ?
                WHERE
                    id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setShort(1, active);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //usuwa użytkownika o podanym id
    public void deleteById(Long id) {
        final String query = """
                DELETE FROM
                    users
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

    //zmienia hasło użytkownika o podanej nazwie
    public void updatePasswordByUsername(String username, String newPassword) {
        final String query = """
                UPDATE 
                users
                SET 
                password = ?
                WHERE 
                username = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //mapper konwertujący obiekt klasy ResultSet na obiekt klasy User
    private User mapRow(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDateTime registrationDate = resultSet.getObject("registration_date_time", LocalDateTime.class);
        short active = resultSet.getShort("active");

        return new User(id, username, email, password, registrationDate, active);
    }
}
