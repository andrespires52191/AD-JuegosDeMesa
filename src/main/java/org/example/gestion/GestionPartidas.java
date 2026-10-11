package org.example.gestion;

import org.example.repositories.PartidaRepositoryImpl;
import org.example.utils.EntradaDatos;

public class GestionPartidas {
    private final PartidaRepositoryImpl repoPartida;

    public GestionPartidas(PartidaRepositoryImpl repoPartida) {

        this.repoPartida = repoPartida;

        final String MENU = "1) Ver lista de Partidas"
                + "\n2) Crear Partida"
                + "\n3) Editar Partida"
                + "\n4) Borrar Partida"
                + "\n5) Exportar partidas.xml"
                + "\n6) Exportar partidas.json";

        int accion = -1;
        while (accion != 5) {
            try {
                accion = Integer.parseInt(EntradaDatos.preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    listar();
                    break;
                case 2:
                    crear();
                    break;
                case 3:
                    editar();
                    break;
                case 4:
                    borrar();
                    break;
                case 5:
                    exportarXML();
                    break;
                case 6:
                    exportarJSON();
                    break;
                default:
                    System.out.println("Acción no válida");
            }
        }
    }

    private void listar() {
    }

    private void crear() {
    }

    private void editar() {
    }

    private void borrar() {
    }

    private void exportarXML() {
    }

    private void exportarJSON() {
    }
}
