package com.diogo.database;

import java.sql.*;

public class DatabaseUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/taskdb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getTaskTitleById(int id) throws SQLException {

        String query = "SELECT title FROM tasks WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("title");
            }

            return null;
        }
    }
}