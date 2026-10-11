package org.example.idao;

import org.example.models.Partida;

import java.util.List;

public interface PartidaDao {
    List<Partida> getPartidas();

    Partida getPartida(int partida_id);

    boolean add(Partida partida);

    boolean update(Partida partida);

    boolean delete(int id_partida);
}
