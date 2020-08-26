package com.purepleasure.pepsicola.database.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ConnectToolsTest {
    Connection connection;
    Statement statement;

    public @Test
    void testGetConnection() {
        connection = DBUtils.getConnection();
        assertNotNull(connection);
        DBUtils.close(connection);
    }

    public @Test
    void testQuery() {
        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            assertNotNull(connection);
            assertNotNull(statement);
            statement.execute("insert into student(Sname, Sage, Ssex) values('米奇', '1986-8-6', '男')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.close(connection);
        }
        assertNull(connection); // 关闭连接并不代表 connection 为 null
    }

    public @Test(expected = SQLException.class)
    void testConnClose() throws SQLException {
        connection = DBUtils.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        // assertNull(statement);  // java.lang.AssertionError: expected null, but was:<com.mysql.cj.jdbc.StatementImpl@15bb6bea>

        // No operations allowed after statement closed.
        statement.execute("insert into student(Sname, Sage, Ssex) values('米奇', '1986-8-6', '男')");


    }

}