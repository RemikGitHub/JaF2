package com.jaf.justaforum.dao;

import com.jaf.justaforum.config.DatabaseConfig;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    private final DataSource dataSource;

    //konstruktor BaseDao
    public BaseDao() {
        try {
            this.dataSource = DatabaseConfig.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
	 
    //metoda zwracająca obiekt Connection potrzebny do komunikacji z bazą danych
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
