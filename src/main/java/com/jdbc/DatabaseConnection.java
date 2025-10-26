package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:sqlserver://GYESISLAP013\\EXPOTUNALOCAL;databaseName=FridgeApp;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
