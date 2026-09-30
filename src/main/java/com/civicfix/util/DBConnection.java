package com.civicfix.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {

        try (InputStream input =
                     DBConnection.class
                         .getClassLoader()
                         .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(
                    "db.properties file not found."
                );
            }

            Properties properties = new Properties();
            properties.load(input);

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (IOException | ClassNotFoundException e) {

            throw new RuntimeException(
                "Failed to initialize database configuration.",
                e
            );
        }
    }

    public static Connection getConnection()
            throws SQLException {
    	

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }
}