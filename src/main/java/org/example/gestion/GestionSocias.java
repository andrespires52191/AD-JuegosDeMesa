package org.example.gestion;

import org.example.models.Socia;
import org.example.repositories.SociaRepositoryImpl;
import org.example.utils.ExportadorJSON;
import org.example.utils.ExportadorXML;
import org.example.utils.SalidaDatos;

import static org.example.utils.EntradaDatos.preguntar;

public class GestionSocias {
    private final SociaRepositoryImpl repoSocia;

    public GestionSocias(SociaRepositoryImpl repoSocia) {

        this.repoSocia = repoSocia;

        final String MENU = "1) Ver lista de Socias"
                + "\n2) Crear Socia"
                + "\n3) Editar Socia"
                + "\n4) Borrar Socia"
                + "\n5) Exportar socias.xml"
                + "\n6) Exportar socias.json"
                + "\n7) Volver al menú principal";

        int accion = -1;
        while (accion != 7) {
            try {
                accion = Integer.parseInt(preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
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
                case 7:
                    // condición de fin de bucle
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }

    private void listar() {
        SalidaDatos.mostrarLista(repoSocia.getSocias());
    }

    private Socia peidrDatos() {
        Socia socia = null;
        try {
            String dni = preguntar("dni: ");
            String nombre = preguntar("nombre: ");
            String apellidos = preguntar("apellidos: ");
            String fechaNac = preguntar("fechaNac: ");
            String telefono = preguntar("telefono: ");
            String email = preguntar("email: ");

            socia = new Socia(dni, nombre, apellidos, fechaNac, telefono, email);
        } catch (Exception ignored) {
        }
        return socia;
    }

    private void crear() {
        Socia socia = peidrDatos();
        if (socia == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        if (repoSocia.add(socia)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void editar() {
        int socia_id = Integer.parseInt(preguntar("id: "));
        Socia socia = repoSocia.getSocia(socia_id);
        if (socia == null) {
            System.out.println("Socia no existe.");
            return;
        }

        Socia sociaActualizada = peidrDatos();
        socia.setDni(sociaActualizada.getDni());
        socia.setNombre(sociaActualizada.getNombre());
        socia.setApellidos(sociaActualizada.getApellidos());
        socia.setFechaNac(sociaActualizada.getFechaNac());
        socia.setTelefono(sociaActualizada.getTelefono());
        socia.setEmail(sociaActualizada.getEmail());

        if (repoSocia.update(socia)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void borrar() {
        int socia_id = Integer.parseInt(preguntar("id: "));
        Socia socia = repoSocia.getSocia(socia_id);
        if (socia == null) {
            System.out.println("Socia no existe.");
            return;
        }

        if (repoSocia.delete(socia_id)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void exportarXML() {
        ExportadorXML.exportar("socias.xml", repoSocia.getSocias(), Socia.class);
    }

    private void exportarJSON() {
        ExportadorJSON.exportar("socias.json", repoSocia.getSocias());
    }
}
