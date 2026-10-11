package org.example.gestion;

import org.example.irepositories.JuegoRepository;
import org.example.utils.EntradaDatos;

public class GestionJuegos {
    private final JuegoRepository juegoRepository;

    public GestionJuegos(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;

        final String MENU = "1) Ver lista de Juegos"
                + "\n2) Crear Juego"
                + "\n3) Editar Juego"
                + "\n4) Borrar Juego"
                + "\n5) Exportar juegos.xml"
                + "\n6) Exportar juegos.json";

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
