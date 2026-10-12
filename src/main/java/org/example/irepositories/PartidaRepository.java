package org.example.irepositories;

import org.example.models.Partida;

import java.util.List;

public interface PartidaRepository {
    List<Partida> getPartidas();

    Partida getPartida(int partidaId);

    boolean add(Partida partida);

    boolean update(Partida partida);

    boolean delete(int partidaId);

    boolean agregarJugador(String jugador);

    boolean quitarJugador(String jugador);
}
