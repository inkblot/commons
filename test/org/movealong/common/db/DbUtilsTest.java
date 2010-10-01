package org.movealong.common.db;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Sep 30, 2010
 * Time: 8:37:54 PM
 */
@RunWith(JMock.class)
public class DbUtilsTest {

    // mockery, mocks, and supports object
    private Mockery mock;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        mock = new JUnit4Mockery();

        connection = mock.mock(Connection.class);
        statement = mock.mock(Statement.class);
        resultSet = mock.mock(ResultSet.class);
    }

    @After
    public void tearDown() throws Exception {
        connection = null;
        statement = null;
        resultSet = null;
        mock = null;
    }

    @Test
    public void testCloseNullConnection() throws Exception {
        DBUtils.close((Connection) null);
    }

    @Test
    public void testCloseNullStatement() throws Exception {
        DBUtils.close((Statement) null);
    }

    @Test
    public void testCloseNullResultSet() throws Exception {
        DBUtils.close((ResultSet) null);
    }

    @Test
    public void testCloseAllNull() throws Exception {
        DBUtils.closeAll(null, null, null);
    }

    @Test
    public void testCloseConnection() throws Exception {
        mock.checking(new Expectations() {{
            one(connection).close();
        }});
        DBUtils.close(connection);
    }

    @Test
    public void testCloseStatement() throws Exception {
        mock.checking(new Expectations() {{
            one(statement).close();
        }});
        DBUtils.close(statement);
    }

    @Test
    public void testCloseResultSet() throws Exception {
        mock.checking(new Expectations() {{
            one(resultSet).close();
        }});
        DBUtils.close(resultSet);
    }

    @Test
    public void testCloseAll() throws Exception {
        mock.checking(new Expectations() {{
            one(connection).close();
            one(statement).close();
            one(resultSet).close();
        }});
        DBUtils.closeAll(connection, statement, resultSet);
    }

    @Test
    public void testCloseBrokenConnection() throws Exception {
        mock.checking(new Expectations() {{
            one(connection).close(); will(throwException(new SQLException("broken")));
        }});
        DBUtils.close(connection);
    }

    @Test
    public void testCloseBrokenStatement() throws Exception {
        mock.checking(new Expectations() {{
            one(statement).close(); will(throwException(new SQLException("broken")));
        }});
        DBUtils.close(statement);
    }

    @Test
    public void testCloseBrokenResultSet() throws Exception {
        mock.checking(new Expectations() {{
            one(resultSet).close(); will(throwException(new SQLException("broken")));
        }});
        DBUtils.close(resultSet);
    }

    @Test
    public void testCloseAllBroken() throws Exception {
        mock.checking(new Expectations() {{
            one(connection).close(); will(throwException(new SQLException("broken")));
            one(statement).close(); will(throwException(new SQLException("broken")));
            one(resultSet).close(); will(throwException(new SQLException("broken")));
        }});
        DBUtils.closeAll(connection, statement, resultSet);
    }
}
