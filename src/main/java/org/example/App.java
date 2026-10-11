package org.example;

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
        Asociacion asociacion = new Asociacion();
        JuegoRepositoryImpl repoJuego = new JuegoRepositoryImpl();
        SociaRepositoryImpl repoSocia = new SociaRepositoryImpl();
        PartidaRepositoryImpl repoPartida = new PartidaRepositoryImpl();

        // titulo inicio
        System.out.println(asociacion);
        System.out.println(asociacion.toString().replaceAll(".", "-"));
        System.out.println();

        final String MENU = "1) Gestionar la Asociación"
                + "\n2) Gestionar los Juegos"
                + "\n2) Gestionar las Socias"
                + "\n3) Gestionar las Partidas"
                + "\n4) Consultar los Metadatos"
                + "\n5) Salir";

        int accion = -1;
        while (accion != 5) {
            try {
                accion = Integer.parseInt(EntradaDatos.preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    new GestionAsociacion();
                    break;
                case 2:
                    new GestionJuegos(repoJuego);
                    break;
                case 3:
                    new GestionSocias(repoSocia);
                    break;
                case 4:
                    new GestionPartidas(repoPartida);
                    break;
                case 5:
                    new ConsultarMetadatos();
                    break;
                default:
                    System.out.println("Acción no válida");
            }
        }
    }
}
