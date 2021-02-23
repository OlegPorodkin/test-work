package ru.porodkin.storage.postgresql.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection extends AbstractConnection{

    public static final Logger LOG = LoggerFactory.getLogger(PostgreConnection.class);

    public PostgreConnection(String url, String login, String password) {
        super(url, login, password);
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException ex) {
            LOG.error("Connection to DB is failed. Check you database or credentials",ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOG.error("Something wrong when connection is close",ex);
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            LOG.error("Something wrong when try connect to DB {}. Please check credentials or internet connections.", url);
            throw new SQLException(ex);
        }

        return connection;
    }
}
