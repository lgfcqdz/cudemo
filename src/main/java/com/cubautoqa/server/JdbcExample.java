package com.cubautoqa.server;

import java.sql.*;

public class JdbcExample {
    // 定义数据库连接信息
    private static final String URL = "jdbc:mysql://192.168.101.118:3306/cudemo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "liugf";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 注册驱动 (对于新版驱动，这一步通常可以省略，它会自动加载)
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 通过DriverManager获取数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 创建一个Statement对象，用于执行SQL语句
            stmt = conn.createStatement();

            // 4. 执行SQL查询语句
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);

            // 5. 处理结果集 (ResultSet)
            while (rs.next()) {
                // 通过列名或索引从结果集中获取数据
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源，必须放在finally块中确保执行，且顺序与创建顺序相反
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
