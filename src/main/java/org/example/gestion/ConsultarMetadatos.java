package org.example.gestion;

import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarMetadatos {
    public ConsultarMetadatos() {
        //Listado y exportación de los metadatos de las bases de datos:
        //BBDD (nombre, driver, url, ...).
        //Tablas de la BBDD (esquema, nombre de la tabla, clave primaria y las columnas que
        //tiene la tabla).
        try {
            Connection conexion = DatabaseConnection.getInstance().getConnection();
            DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto DatabaseMetaData

            ResultSet resul = null;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
            System.out.println("==================================");
            System.out.println("Nombre: " + nombre);
            System.out.println("Driver: " + driver);
            System.out.println("URL:" + url);
            System.out.println("Usuario: " + usuario);

            resul = dbmd.getTables(null, "ejemplo", null, null);
            while (resul.next()) {
                String catalogo = resul.getString(1);//columna 1 que devuelve ResulSet
                String esquema = resul.getString(2); //columna 2
                String tabla = resul.getString(3);   //columna 3
                String tipo = resul.getString(4);
                //columna 4
                System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Nombre : " + tabla);
            } //Fin del while

            System.out.println();
            System.out.println("COLUMNAS TABLA DEPARTAMENTOS");
            System.out.println("============================");

            String[] nombresTablas = {"juegos", "socias", "partidas"};
            for (String nombreTabla : nombresTablas) {
                System.out.println(nombreTabla);
                System.out.println("----------------------------");

                ResultSet columnas = null;
                columnas = dbmd.getColumns(null, "ejemplo", nombreTabla, null);
                while (columnas.next()) {
                    String nombCol = columnas.getString("COLUMN_NAME");
                    String tipoCol = columnas.getString("TYPE_NAME");
                    String tamCol = columnas.getString("COLUMN_SIZE");
                    String nula = columnas.getString("IS_NULLABLE");
                    System.out.printf("Columna:%s, Tipo:%s, Tamaño: %s, ¿Puede ser nula? %s", nombCol, tipoCol, tamCol, nula);
                }
            }
        } //Fin del try
        catch (SQLException e) {
            System.out.println("Error consiguiendo metadatos.");
        }
        System.out.println();
    } //Fin del main
} //Fin de la clase
