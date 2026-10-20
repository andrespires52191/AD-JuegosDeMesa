package org.example.gestion;

import org.example.irepositories.JuegoRepository;
import org.example.irepositories.SociaRepository;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Socia;
import org.example.utils.EntradaDatos;
import org.example.utils.ExportadorJSON;
import org.example.utils.ExportadorXML;
import org.example.utils.SalidaDatos;

import java.time.LocalDate;

import static org.example.utils.EntradaDatos.preguntar;

public class GestionJuegos {
    private final JuegoRepository repoJuego;
    private final SociaRepository repoSocia;

    public GestionJuegos(JuegoRepository repoJuego, SociaRepository repoSocia) {
        this.repoJuego = repoJuego;
        this.repoSocia = repoSocia;

        final String MENU = "1) Ver lista de Juegos"
                + "\n2) Crear Juego"
                + "\n3) Editar Juego"
                + "\n4) Borrar Juego"
                + "\n5) Exportar juegos.xml"
                + "\n6) Exportar juegos.json"
                + "\n7) Volver al menú principal";

        int accion = -1;
        while (accion != 7) {
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
        SalidaDatos.mostrarLista(repoJuego.getJuegos());
    }

    private Juego peidrDatos() {
        Juego juego = null;
        try {
            String nombre = preguntar("nombre: ");
            String autores = preguntar("autores: ");
            int minJugadores = Integer.parseInt(preguntar("minJugadores: "));
            int maxJugadores = Integer.parseInt(preguntar("maxJugadores: "));
            double estimacionMinutos = Double.parseDouble(preguntar("estimacionMinutos: "));
            String tematica = preguntar("tematica: ");
            String ubicacion = preguntar("ubicacion: ");
            JuegoEstado estado = JuegoEstado.valueOf(preguntar("estado: ").toUpperCase());
            String propietaria_str = preguntar("propietaria_id: ");
            LocalDate fechaAlta = LocalDate.parse(preguntar("fechaAlta: "));

            int propietaria_id = propietaria_str.isEmpty() ? 0 : Integer.parseInt(propietaria_str);
            Socia propietaria = null;
            if (propietaria_id > 0) {
                propietaria = repoSocia.getSocia(propietaria_id);
            }

            juego = new Juego(nombre, autores, minJugadores, maxJugadores, estimacionMinutos,
                    tematica, ubicacion, estado, propietaria, fechaAlta);
        } catch (Exception ignored) {
        }
        return juego;
    }

    private void crear() {
        Juego juego = peidrDatos();
        if (juego == null) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        if (repoJuego.add(juego)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void editar() {
        int juego_id = 0;
        try {
            juego_id = Integer.parseInt(EntradaDatos.preguntar("id: "));
        } catch (NumberFormatException e) {
            System.out.println("Fallo al leer datos");
            return;
        }

        Juego juego = repoJuego.getJuego(juego_id);
        if (juego == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }

        Juego juegoActualizado = peidrDatos();
        if (juegoActualizado == null) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        juego.setNombre(juegoActualizado.getNombre());
        juego.setAutores(juegoActualizado.getAutores());
        juego.setMinJugadores(juegoActualizado.getMinJugadores());
        juego.setMaxJugadores(juegoActualizado.getMaxJugadores());
        juego.setEstimacionMinutos(juegoActualizado.getEstimacionMinutos());
        juego.setTematica(juegoActualizado.getTematica());
        juego.setUbicacion(juegoActualizado.getUbicacion());
        juego.setEstado(juegoActualizado.getEstado());
        juego.setPropietaria(juegoActualizado.getPropietaria());
        juego.setFechaAlta(juegoActualizado.getFechaAlta());

        if (repoJuego.update(juego)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void borrar() {
        int juego_id = 0;
        try {
            juego_id = Integer.parseInt(EntradaDatos.preguntar("id: "));
        } catch (NumberFormatException e) {
            System.out.println("Fallo al leer datos");
            return;
        }

        Juego juego = repoJuego.getJuego(juego_id);
        if (juego == null) {
            System.out.println("Juego no existe.");
            return;
        }

        if (repoJuego.delete(juego_id)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void exportarXML() {
        if (ExportadorXML.exportar("juegos.xml", repoJuego.getJuegos(), Juego.class)) {
            System.out.println("Exportado con éxito.");
        } else {
            System.out.println("Fallo al exportar.");
        }
    }

    private void exportarJSON() {
        if (ExportadorJSON.exportar("juegos.json", repoJuego.getJuegos())) {
            System.out.println("Exportado con éxito.");
        } else {
            System.out.println("Fallo al exportar.");
        }
    }
}
