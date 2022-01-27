package com.jaf.justaforum.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConfig {

    private static DataSource dataSource;

    //prywatny konstruktor, aby nie tworzyć instancji klasy
    private DatabaseConfig() { }

    //konfiguruje bazę danych mysql
    public static DataSource getDataSource() throws NamingException {
        if (dataSource == null) {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env/");
            dataSource = (DataSource) envContext.lookup("jdbc/database");
        }
        return dataSource;
    }
}
