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
import org.example.utils.EntradaDatos;

public class App {
    public static void main(String[] args) {
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
        PartidaRepositoryImpl repoPartida = new PartidaRepositoryImpl(partidaDao);

        // titulo inicio
        Asociacion asociacion = asociacionDao.getAsociacion();
        System.out.println(asociacion);
        System.out.println(asociacion.toString().replaceAll(".", "="));
        System.out.println();

        final String MENU = "1) Gestionar la Asociación"
                + "\n2) Gestionar los Juegos"
                + "\n3) Gestionar las Socias"
                + "\n4) Gestionar las Partidas"
                + "\n5) Consultar los Metadatos"
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
                    new GestionSocias(repoSocia);
                    break;
                case 4:
                    new GestionPartidas(repoPartida, repoJuego);
                    break;
                case 5:
                    new ConsultarMetadatos();
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
