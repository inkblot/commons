package org.movealong.common.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Sep 6, 2010
 * Time: 9:12:28 AM
 */
public class DBUtils {

    private DBUtils() {
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // ok
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // ok
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // ok
            }
        }
    }

    public static DataSource getDataSource(String jndiName) {
        try {
            InitialContext context = new InitialContext();
            return (DataSource) context.lookup(jndiName);
        } catch (NamingException e) {
            throw new RuntimeException("Could not create InitialContext", e);
        }
    }

}
