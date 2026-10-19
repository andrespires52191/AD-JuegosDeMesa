package org.example;

import org.example.dao.AsociacionDaoImpl;
import org.example.dao.JuegoDaoImpl;
import org.example.dao.PartidaDaoImpl;
import org.example.dao.SociaDaoImpl;
import org.example.gestion.*;
import org.example.models.Asociacion;
import org.example.repositories.JuegoRepositoryImpl;
import org.example.repositories.PartidaRepositoryImpl;
import org.example.repositories.SociaRepositoryImpl;
import org.example.utils.DatabaseConnection;
import org.example.utils.EntradaDatos;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            DatabaseConnection.getInstance();
        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos.");
            return;
        }

        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        // DAOs
        AsociacionDaoImpl asociacionDao = new AsociacionDaoImpl();
        JuegoDaoImpl juegoDao = new JuegoDaoImpl();
        SociaDaoImpl sociaDao = new SociaDaoImpl();
        PartidaDaoImpl partidaDao = new PartidaDaoImpl();

        // Repos
        JuegoRepositoryImpl repoJuego = new JuegoRepositoryImpl(juegoDao);
        SociaRepositoryImpl repoSocia = new SociaRepositoryImpl(sociaDao);
        PartidaRepositoryImpl repoPartida = new PartidaRepositoryImpl(partidaDao, juegoDao);

        // titulo inicio
        Asociacion asociacion = asociacionDao.getAsociacion();
        System.out.println(asociacion);
        System.out.println(asociacion.toString().replaceAll(".", "="));
        System.out.println();

        final String MENU = "1) Gestionar la Asociación"
                + "\n2) Gestionar los Juegos"
                + "\n3) Gestionar las Socias"
                + "\n4) Gestionar las Partidas"
                + "\n5) Gestionar los Metadatos"
                + "\n6) Salir";

        int accion = -1;
        while (accion != 6) {
            try {
                accion = Integer.parseInt(EntradaDatos.preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    new GestionAsociacion(asociacionDao);
                    break;
                case 2:
                    new GestionJuegos(repoJuego, repoSocia);
                    break;
                case 3:
                    new GestionSocias(repoSocia, repoJuego, repoPartida);
                    break;
                case 4:
                    new GestionPartidas(repoPartida, repoJuego, repoSocia);
                    break;
                case 5:
                    new GestionMetadatos();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Acción no válida");
            }
        }
    }
}
