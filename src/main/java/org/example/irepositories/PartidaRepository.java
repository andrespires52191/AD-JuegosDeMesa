package org.example.irepositories;

import org.example.models.Partida;
import org.example.models.Socia;

import java.util.List;

public interface PartidaRepository {
    List<Partida> getPartidas();

    Partida getPartida(int partidaId);

    boolean add(Partida partida);

    boolean update(Partida partida);

    boolean delete(int partidaId);

    List<String> getJugadoras(int partidaId);

    boolean agregarJugadoraInvitada(int partidaId, String invitada);

    boolean agregarJugadoraSocia(int partidaId, Socia socia);

    boolean quitarJugadoraInvitada(int partidaId, String invitada);

    boolean quitarJugadoraSocia(int partidaId, Socia socia);
}
