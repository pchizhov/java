package db;

import java.sql.*;

public class Connector {

    private static Connection connection = null;

    public Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/pavelchizhov/data/EnglishSchool.db");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
