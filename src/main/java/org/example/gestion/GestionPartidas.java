package org.example.gestion;

import org.example.models.Juego;
import org.example.models.Partida;
import org.example.models.Socia;
import org.example.repositories.JuegoRepositoryImpl;
import org.example.repositories.PartidaRepositoryImpl;
import org.example.repositories.SociaRepositoryImpl;
import org.example.utils.ExportadorJSON;
import org.example.utils.ExportadorXML;
import org.example.utils.SalidaDatos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.example.utils.EntradaDatos.preguntar;

public class GestionPartidas {
    private final PartidaRepositoryImpl repoPartida;
    private final JuegoRepositoryImpl repoJuego;
    private final SociaRepositoryImpl repoSocia;

    public GestionPartidas(PartidaRepositoryImpl repoPartida, JuegoRepositoryImpl repoJuego, SociaRepositoryImpl repoSocia) {
        this.repoPartida = repoPartida;
        this.repoJuego = repoJuego;
        this.repoSocia = repoSocia;

        final String MENU = "1) Ver lista de Partidas"
                + "\n2) Mostrar detalles de Partida"
                + "\n3) Crear Partida"
                + "\n4) Editar Partida"
                + "\n5) Borrar Partida"
                + "\n6) Agregar Jugadoras"
                + "\n7) Quitar Jugadoras"
                + "\n8) Exportar partidas.xml"
                + "\n9) Exportar partidas.json"
                + "\n10) Volver al menú principal";

        int accion = -1;
        while (accion != 10) {
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
                    mostrar();
                    break;
                case 3:
                    crear();
                    break;
                case 4:
                    editar();
                    break;
                case 5:
                    borrar();
                    break;
                case 6:
                    agregarJugadora();
                    break;
                case 7:
                    quitarJugadora();
                    break;
                case 8:
                    exportarXML();
                    break;
                case 9:
                    exportarJSON();
                    break;
                case 10:
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

    private void mostrar() {
        int partida_id = 0;
        try {
            partida_id = Integer.parseInt(preguntar("id: "));
        } catch (NumberFormatException e) {
            System.out.println("Fallo al leer datos");
            return;
        }

        Partida partida = repoPartida.getPartida(partida_id);
        if (partida == null) {
            System.out.println("Partida no existe.");
            return;
        }

        List<String> jugadoras = repoPartida.getJugadoras(partida.getId());

        System.out.println("id: " + partida.getId());
        System.out.println("juego: " + partida.getJuego());
        System.out.println("fecha: " + partida.getFecha());
        System.out.println("hora: " + partida.getHora());
        System.out.println("plazasDeseadas: " + partida.getPlazasDeseadas());
        System.out.println("jugadoras:");
        for (String j : jugadoras) {
            System.out.println("\t- " + j);
        }
    }

    private Partida peidrDatos() {
        Partida partida = null;
        try {
            String juego_str = preguntar("juego_id: ");
            LocalDate fecha = LocalDate.parse(preguntar("fecha: "));
            int plazasDeseadas = Integer.parseInt(preguntar("plazasDeseadas: "));
            LocalTime hora = LocalTime.parse(preguntar("hora: "));

            int juego_id = juego_str.isEmpty() ? 0 : Integer.parseInt(juego_str);
            Juego juego = null;
            if (juego_id > 0) {
                juego = repoJuego.getJuego(juego_id);
            }
            if (juego == null) {
                return null;
            }

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
        int partida_id = 0;
        try {
            partida_id = Integer.parseInt(preguntar("id: "));
        } catch (NumberFormatException e) {
            System.out.println("Fallo al leer datos");
            return;
        }

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

        int plazasOcupadas = repoPartida.getJugadoras(partida_id).size();
        if (plazasOcupadas > partidaActualizada.getPlazasDeseadas()) {
            System.out.println("Ya hay " + plazasOcupadas + " plazas ocupadas, quita jugadoras e intenta de nuevo.");
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
        int partida_id = 0;
        try {
            partida_id = Integer.parseInt(preguntar("id: "));
        } catch (NumberFormatException e) {
            System.out.println("Fallo al leer datos");
            return;
        }

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

    private void agregarJugadora() {
        int partida_id = 0;
        try {
            partida_id = Integer.parseInt(preguntar("id de partida: "));
        } catch (NumberFormatException ignored) {
        }

        Partida partida = repoPartida.getPartida(partida_id);
        if (partida == null) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        if (repoPartida.getJugadoras(partida_id).size() >= partida.getPlazasDeseadas()) {
            System.out.println("No quedan más plazas disponibles.");
            return;
        }

        String respuesta = preguntar("id de socia o nombre de invitada: ");
        if (respuesta.isEmpty()) {
            System.out.println("El campo no puede estar vacío");
            return;
        }

        List<String> jugadoras = repoPartida.getJugadoras(partida_id);

        boolean resultado;
        if (Character.isDigit(respuesta.charAt(0))) {
            Socia jugadoraSocia = null;
            try {
                jugadoraSocia = repoSocia.getSocia(Integer.parseInt(respuesta));
            } catch (NumberFormatException ignored) {
            }

            if (jugadoraSocia == null) {
                System.out.println("Fallo al conseguir los datos");
                return;
            }

            if (jugadoras.contains("Socia #" + jugadoraSocia.getId())) {
                System.out.println("La jugadora ya está en la lista");
                return;
            }

            resultado = repoPartida.agregarJugadoraSocia(partida_id, jugadoraSocia);
        } else {
            String jugadoraInvitada = respuesta;

            if (jugadoras.contains(jugadoraInvitada)) {
                System.out.println("La jugadora ya está en la lista");
                return;
            }

            resultado = repoPartida.agregarJugadoraInvitada(partida_id, jugadoraInvitada);
        }

        if (resultado) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void quitarJugadora() {
        int partida_id = 0;
        try {
            partida_id = Integer.parseInt(preguntar("id de partida: "));
        } catch (NumberFormatException ignored) {
        }

        if (partida_id == 0) {
            System.out.println("Fallo al conseguir los datos");
            return;
        }

        String respuesta = preguntar("id de socia o nombre de invitada: ");
        if (respuesta.isEmpty()) {
            System.out.println("El campo no puede estar vacío");
            return;
        }

        List<String> jugadoras = repoPartida.getJugadoras(partida_id);

        boolean resultado;
        if (Character.isDigit(respuesta.charAt(0))) {
            Socia jugadoraSocia = null;
            try {
                jugadoraSocia = repoSocia.getSocia(Integer.parseInt(respuesta));
            } catch (NumberFormatException ignored) {
            }

            if (jugadoraSocia == null) {
                System.out.println("Fallo al conseguir los datos");
                return;
            }

            if (!jugadoras.contains("Socia #" + jugadoraSocia.getId())) {
                System.out.println("La jugadora no está en la lista");
                return;
            }

            resultado = repoPartida.quitarJugadoraSocia(partida_id, jugadoraSocia);
        } else {
            String jugadoraInvitada = respuesta;

            if (!jugadoras.contains(jugadoraInvitada)) {
                System.out.println("La jugadora no está en la lista");
                return;
            }

            resultado = repoPartida.quitarJugadoraInvitada(partida_id, jugadoraInvitada);
        }

        if (resultado) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }

    private void exportarXML() {
        if (ExportadorXML.exportar("partidas.xml", repoPartida.getPartidas(), Partida.class)) {
            System.out.println("Exportado con éxito.");
        } else {
            System.out.println("Fallo al exportar.");
        }
    }

    private void exportarJSON() {
        if (ExportadorJSON.exportar("partidas.json", repoPartida.getPartidas())) {
            System.out.println("Exportado con éxito.");
        } else {
            System.out.println("Fallo al exportar.");
        }
    }
}
