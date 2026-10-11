package org.example.gestion;

import org.example.repositories.SociaRepositoryImpl;
import org.example.utils.EntradaDatos;

public class GestionSocias {
    private final SociaRepositoryImpl repoSocia;

    public GestionSocias(SociaRepositoryImpl repoSocia) {

        this.repoSocia = repoSocia;

        final String MENU = "1) Ver lista de Socias"
                + "\n2) Crear Socia"
                + "\n3) Editar Socia"
                + "\n4) Borrar Socia"
                + "\n5) Exportar socias.xml"
                + "\n6) Exportar socias.json";

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
