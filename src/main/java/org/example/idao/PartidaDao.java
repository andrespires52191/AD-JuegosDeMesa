package org.example.idao;

import org.example.models.Partida;
import org.example.models.Socia;

import java.util.List;

public interface PartidaDao {
    List<Partida> getPartidas();

    Partida getPartida(int partida_id);

    boolean add(Partida partida);

    boolean update(Partida partida);

    boolean delete(int id_partida);

    List<String> getJugadoras(int partida_id);

    boolean agregarJugadoraInvitada(int partidaId, String invitada);

    boolean agregarJugadoraSocia(int partidaId, Socia socia);

    boolean quitarJugadoraInvitada(int partidaId, String invitada);

    boolean quitarJugadoraSocia(int partidaId, Socia socia);
}
