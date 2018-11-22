package db;

import java.sql.*;

public class Connector {

    private static AdvConnection connection = new AdvConnection();

    public static void disconnect() {
        try {
            connection.getConnection().close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static AdvConnection getConnection() {
        return connection;
    }

}
