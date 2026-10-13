package org.example.gestion;

import org.example.models.Juego;
import org.example.models.Partida;
import org.example.repositories.JuegoRepositoryImpl;
import org.example.repositories.PartidaRepositoryImpl;
import org.example.utils.ExportadorJSON;
import org.example.utils.ExportadorXML;
import org.example.utils.SalidaDatos;

import static org.example.utils.EntradaDatos.preguntar;

public class GestionPartidas {
    private final PartidaRepositoryImpl repoPartida;
    private final JuegoRepositoryImpl repoJuego;

    public GestionPartidas(PartidaRepositoryImpl repoPartida, JuegoRepositoryImpl repoJuego) {
        this.repoPartida = repoPartida;
        this.repoJuego = repoJuego;

        final String MENU = "1) Ver lista de Partidas"
                + "\n2) Crear Partida"
                + "\n3) Editar Partida"
                + "\n4) Borrar Partida"
                + "\n5) Agregar Jugadores"
                + "\n6) Quitar Jugadores"
                + "\n7) Exportar partidas.xml"
                + "\n8) Exportar partidas.json"
                + "\n9) Volver al menú principal";

        int accion = -1;
        while (accion != 9) {
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
                    agregarJugadores();
                    break;
                case 6:
                    quitarJugadores();
                    break;
                case 7:
                    exportarXML();
                    break;
                case 8:
                    exportarJSON();
                    break;
                case 9:
                    // condición de fin de bucle
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }

    private void listar() {
        SalidaDatos.mostrarLista(repoPartida.getPartidas());
    }

    private Partida peidrDatos() {
        Partida partida = null;
        try {
            String juego_str = preguntar("juego_id: ");
            String fecha = preguntar("fecha: ");
            int plazasDeseadas = Integer.parseInt(preguntar("plazasDeseadas: "));
            String hora = preguntar("hora: ");

            int juego_id = juego_str.isEmpty() ? 0 : Integer.parseInt(juego_str);
            Juego juego = null;
            if (juego_id > 0) {
                juego = repoJuego.getJuego(juego_id);
            }
            assert juego != null;

            partida = new Partida(juego, fecha, hora, plazasDeseadas);
        } catch (Exception ignored) {
        }
        return partida;
    }

    private void crear() {
        Partida partida = peidrDatos();
        if (partida == null) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        if (repoPartida.add(partida)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void editar() {
        int partida_id = Integer.parseInt(preguntar("id: "));
        Partida partida = repoPartida.getPartida(partida_id);
        if (partida == null) {
            System.out.println("Partida no existe.");
            return;
        }

        Partida partidaActualizada = peidrDatos();
        if (partidaActualizada == null) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        partida.setJuego(partidaActualizada.getJuego());
        partida.setFecha(partidaActualizada.getFecha());
        partida.setPlazasDeseadas(partidaActualizada.getPlazasDeseadas());
        partida.setHora(partidaActualizada.getHora());

        if (repoPartida.update(partida)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void borrar() {
        int partida_id = Integer.parseInt(preguntar("id: "));
        Partida partida = repoPartida.getPartida(partida_id);
        if (partida == null) {
            System.out.println("Partida no existe.");
            return;
        }

        if (repoPartida.delete(partida_id)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void agregarJugadores() {
        String jugador = preguntar("nombre de jugador: ");
        if (repoPartida.agregarJugador(jugador)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void quitarJugadores() {
        String jugador = preguntar("nombre de jugador: ");
        if (repoPartida.quitarJugador(jugador)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void exportarXML() {
        ExportadorXML.exportar("partidas.xml", repoPartida.getPartidas(), Partida.class);
    }

    private void exportarJSON() {
        ExportadorJSON.exportar("partidas.json", repoPartida.getPartidas());
    }
}
