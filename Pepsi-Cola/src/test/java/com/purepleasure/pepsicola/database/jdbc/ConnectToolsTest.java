package com.purepleasure.pepsicola.database.jdbc;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class ConnectToolsTest {

    Connection connection;
    Connection connection1;
    Connection connection2;
    Connection connection3;
    Statement statement;
    Statement statement1;
    PreparedStatement preparedStatement;
    ResultSet rs, rs1;

    public @Test
    void testGetConnection() {
        System.out.println(3);
        connection1 = DBUtils.getConnection();
        connection2 = DBUtils.getConnection("jdbc:mysql://127.0.0.1:3306/train", "root", "root");
        connection3 = DBUtils.getConnection();

        System.out.println(
                connection1.toString() +
                        "\n" + connection2.toString() +
                        "\n" + connection3.toString()
                // 不相同
        );

        assertNotNull(connection1);
        assertNotNull(connection2);
        assertNotNull(connection3);

        DBUtils.close(connection1); // 关闭连接是怎么一回事？？
        DBUtils.close(connection2);
        DBUtils.close(connection3);

        // 关闭连接后 connection* 对象的状态，connection为什么不为null
        assertNotNull(connection1);
        assertNotNull(connection2);
        assertNotNull(connection3);
    }

//    public @Test
//    void testGetConnections() {
//        while(true){
//            DBUtils.getConnection();
//            // Data source rejected establishment of connection,  message from server: "Too many connections"
//        }
//    }

    public @Test
    void testQuery() {
        connection = DBUtils.getConnection();
        statement = DBUtils.getStatement(connection);
        statement1 = DBUtils.getStatement(connection);
        preparedStatement = DBUtils.getPreparedStatement(connection, "insert into student(Sname, Sage, Ssex) values(?, ?, ?)");
        boolean result = false;
        int affected = 0;

        try {
            result = statement.execute("insert into student(Sname, Sage, Ssex) values('米奇', '1986-8-6', '男')");
            assertEquals(false, result);

            result = statement.execute("UPDATE student SET Sname='兔八哥' WHERE sname = '米奇'");
            assertEquals(false, result);

            affected = statement.executeUpdate("UPDATE student SET Sname='米奇' WHERE sname = '兔八哥'");
            System.out.println(affected);

            rs = statement.executeQuery("select sid from student where sname = '米奇'");
//            rs1 = statement.executeQuery("select sid from student where sname = 'Kongwei_Liao'");
            while (rs.next()) {
                System.out.println(rs.getInt("sid"));
            }

            preparedStatement.setString(1, "米奇");
            preparedStatement.setString(2, "1986-8-6");
            preparedStatement.setString(3, "男");

            affected = preparedStatement.executeUpdate();
            System.out.println(affected);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement);
            DBUtils.close(rs);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
    }
}