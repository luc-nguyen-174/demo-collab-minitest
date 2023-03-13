package com.demo_collab.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/demo_user_management?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Nguyenluc97";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
