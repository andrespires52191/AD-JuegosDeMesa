package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            String driver = "org.mariadb.jdbc.Driver";
            //String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            //String url = "jdbc:mariadb://localhost:33060/practica200";
            //String url = "jdbc:mysql://localhost/practica200";
            String url = "jdbc:mariadb://localhost/practica200";
            String user = "root";
            String password = ""; // "12345Abcde";

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
