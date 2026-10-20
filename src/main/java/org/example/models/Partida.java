package org.example.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//Listado de partidas de la asociación. Cada partida tendrá asociadas una serie de
//jugadoras (ya sean socias o invitadas), un juego, una fecha, una hora, una cantidad
//de plazas libres y un número de jugadores deseados (dentro del rango del juego).
//Posibilidad de exportar dichos datos como fichero XML y JSON.
public class Partida {
    int id;
    List<String> jugadoras;
    Juego juego;
    LocalDate fecha;
    LocalTime hora;
    int plazasDeseadas;

    public Partida(Juego juego, LocalDate fecha, LocalTime hora, int plazasDeseadas) {
        this.juego = juego;
        this.fecha = fecha;
        this.hora = hora;
        this.plazasDeseadas = plazasDeseadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJugadoras() {
        return jugadoras;
    }

    public void setJugadoras(List<String> jugadoras) {
        this.jugadoras = jugadoras;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPlazasDeseadas() {
        return plazasDeseadas;
    }

    public void setPlazasDeseadas(int plazasDeseadas) {
        this.plazasDeseadas = plazasDeseadas;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Partida #" + id
                + " - " + juego.getNombre()
                + " - " + fecha
                + " " + hora;
    }
}
