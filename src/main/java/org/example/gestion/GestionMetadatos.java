package org.example.gestion;

import org.example.utils.DatabaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.utils.EntradaDatos.preguntar;

public class GestionMetadatos {
    public GestionMetadatos() {
        //Listado y exportación de los metadatos de las bases de datos:
        //BBDD (nombre, driver, url, ...).
        //Tablas de la BBDD (esquema, nombre de la tabla, clave primaria y las columnas que
        //tiene la tabla).

        final String MENU = "1) Ver metadatos"
                + "\n2) Exportar metadatos"
                + "\n3) Volver al menú principal";

        int accion = -1;
        while (accion != 3) {
            try {
                accion = Integer.parseInt(preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    verMetadatos();
                    break;
                case 2:
                    exportarMetadatos();
                    break;
                case 3:
                    // condición de fin de bucle
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }

    String extraerMetadatos() {
        String metadatos = "";

        try {
            Connection conexion = DatabaseConnection.getInstance().getConnection();
            DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto DatabaseMetaData

            ResultSet resul = null;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            metadatos += "INFORMACIÓN SOBRE LA BASE DE DATOS" + "\n";
            metadatos += "==================================" + "\n";
            metadatos += "Nombre: " + nombre + "\n";
            metadatos += "Driver: " + driver + "\n";
            metadatos += "URL:" + url + "\n";
            metadatos += "Usuario: " + usuario + "\n";

            metadatos += "\n";

            resul = dbmd.getTables("juegos_de_mesa", null, null, null);
            while (resul.next()) {
                String catalogo = resul.getString(1);//columna 1 que devuelve ResulSet
                String esquema = resul.getString(2); //columna 2
                String tabla = resul.getString(3);   //columna 3
                String tipo = resul.getString(4);    //columna 4
                metadatos += tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Nombre : " + tabla + "\n";
            } //Fin del while

            metadatos += "\n";
            metadatos += "COLUMNAS DE TABLAS\n";
            metadatos += "============================\n";

            String[] nombresTablas = {"asociacion", "juegos", "socias", "partidas", "partidas_jugadoras"};
            for (String nombreTabla : nombresTablas) {
                metadatos += "\n";
                metadatos += nombreTabla + "\n";
                metadatos += "----------------------------\n";

                ResultSet columnas = null;
                columnas = dbmd.getColumns("juegos_de_mesa", null, nombreTabla, null);
                while (columnas.next()) {
                    String nombCol = columnas.getString("COLUMN_NAME");
                    String tipoCol = columnas.getString("TYPE_NAME");
                    String tamCol = columnas.getString("COLUMN_SIZE");
                    String nula = columnas.getString("IS_NULLABLE");
                    metadatos += String.format("Columna: %s, Tipo:%s, Tamaño: %s, ¿Puede ser nula? %s\n", nombCol, tipoCol, tamCol, nula);
                }
            }
            return metadatos;
        } //Fin del try
        catch (SQLException e) {
            System.out.println("Error consiguiendo metadatos.");
        }
        return "";
    }

    private void verMetadatos() {
        String metadatos = extraerMetadatos();
        if (metadatos.isEmpty()) {
            System.out.println("Error al extraer metadatos");
        } else {
            System.out.println(metadatos);
        }
    }

    private void exportarMetadatos() {

        String metadatos = extraerMetadatos();
        if (metadatos.isEmpty()) {
            System.out.println("Error al extraer metadatos");
        } else {

            // asegurarse de que existe carpeta de salida
            File carpeta = new File("datos_exportados");
            carpeta.mkdirs();
            if (!carpeta.exists()) {
                System.out.println("Error: fallo al crear carpeta de salida");
                return;
            }

            try {
                File archivo = new File(carpeta, "metadatos.txt");
                FileOutputStream fos = new FileOutputStream(archivo);
                fos.write(metadatos.getBytes());
                fos.close();
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo");
            }
        }
    }
}
