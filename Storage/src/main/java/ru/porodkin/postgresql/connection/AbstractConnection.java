package ru.porodkin.postgresql.connection;

import java.sql.Connection;
import java.sql.SQLException;

abstract class AbstractConnection {
    protected final String url;
    protected final String login;
    protected final String password;

    public AbstractConnection(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public abstract Connection getConnection() throws SQLException;
}
