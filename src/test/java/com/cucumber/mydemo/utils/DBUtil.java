package com.cucumber.mydemo.utils;

import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connect() throws SQLException {
        Properties config = ConfigReader.getInstance().getProperties(); // 获取配置
        String url = config.getProperty("db.url");
        String user = config.getProperty("db.username");
        String password = config.getProperty("db.password");
        connection = DriverManager.getConnection(url, user, password);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public static int executeUpdate(String query) throws SQLException {
        statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public static void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 示例：根据用户名查询用户
    public static boolean userExists(String username) throws SQLException {
        connect();
        String query = "SELECT COUNT(*) FROM users WHERE username = '" + username + "'";
        ResultSet rs = executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        close();
        return count > 0;
    }
}