package org.example.models;

import java.util.List;

//Listado de partidas de la asociación. Cada partida tendrá asociadas una serie de
//jugadoras (ya sean socias o invitadas), un juego, una fecha, una hora, una cantidad
//de plazas libres y un número de jugadores deseados (dentro del rango del juego).
//Posibilidad de exportar dichos datos como fichero XML y JSON.
public class Partida {
    int id;
    List<String> jugadoras;
    Juego juego;
    int juego_id;
    String fecha;
    String hora;
    int plazasDeseadas;

    public Partida(Juego juego, String fecha, String hora, int plazasDeseadas) {
        this.juego = juego;
        this.fecha = fecha;
        this.hora = hora;
        this.plazasDeseadas = plazasDeseadas;
    }

    public Partida(int juego_id, String fecha, String hora, int plazasDeseadas) {
        this.juego_id = juego_id;
        this.fecha = fecha;
        this.hora = hora;
        this.plazasDeseadas = plazasDeseadas;
    }

    public int plazasLibres() {
        return plazasDeseadas - jugadoras.size();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPlazasDeseadas() {
        return plazasDeseadas;
    }

    public void setPlazasDeseadas(int plazasDeseadas) {
        this.plazasDeseadas = plazasDeseadas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
