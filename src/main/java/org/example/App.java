package org.example;

import org.example.models.Asociacion;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Asociacion asociacion = new Asociacion();
        System.out.println(asociacion);

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
