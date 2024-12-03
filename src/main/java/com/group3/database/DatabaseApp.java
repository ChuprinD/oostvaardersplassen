package com.group3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseApp {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oostvaardersplassen";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12weuser321!"; 

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}

