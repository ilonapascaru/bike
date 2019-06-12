package mysql;

import java.sql.Connection;

public class DatabaseDetails {
    public String user;
    public String database;
    Connection conn;

    public DatabaseDetails(String user, String database, Connection conn) {
        this.user = user;
        this.database = database;
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
