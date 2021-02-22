package ru.porodkin.postgresql.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection extends AbstractConnection{

    public static final Logger log = LoggerFactory.getLogger(PostgreConnection.class);

    public PostgreConnection(String url, String login, String password) {
        super(url, login, password);
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            log.error("Something wrong when try connect to DB {}. Please check credentials or internet connections.", url);
            throw new SQLException(ex);
        }
    }
}
