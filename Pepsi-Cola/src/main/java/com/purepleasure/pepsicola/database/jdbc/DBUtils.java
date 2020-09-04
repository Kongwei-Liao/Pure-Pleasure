package com.purepleasure.pepsicola.database.jdbc;

import java.sql.*;

public class DBUtils {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 初始化驱动类com.mysql.jdbc.Driver
            // Class.forName 是把这个类加载到JVM中，加载的时候，就会执行其中的静态初始化块，完成驱动的初始化的相关工作。
            Class.forName("com.mysql.cj.jdbc.Driver");  // com.mysql.jdbc.Driver       Loading class `com.mysql.jdbc.Driver'. This is deprecated.
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/train?serverTimezone=UTC" +
                    "&characterEncoding=UTF-8", "root", "root");
            // 该类就在 mysql-connector-java-*.*.*-bin.jar中，如果忘记了导入该jar包，就会抛出ClassNotFoundException
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + "?serverTimezone=UTC" +
                    "&characterEncoding=UTF-8", user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public PreparedStatement getPreparedStatement(@org.jetbrains.annotations.NotNull Connection connection, String sql) {
        try {
            return (PreparedStatement)connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 封装三个关闭方法
     *
     * @param pstmt
     */
    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {                        //避免出现空指针异常
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
