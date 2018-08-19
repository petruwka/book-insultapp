package org.openshift;

import java.sql.*;

public class ConnectionFactory {

    public static Connection getConnection() {
        String jdbcProtocol = "jdbc://postgresql://";
        String host = System.getenv("POSTGRESQL_SERVICE_HOST");
        String database = System.getenv("POSTGRESQL_DATABASE");
        String user = System.getenv("POSTGRESQL_USER");
        String pass = System.getenv("POSTGRESQL_PASSWORD");

        String url = new StringBuilder()
                .append(jdbcProtocol)
                .append(host)
                .append("/")
                .append(database)
                .toString();

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
