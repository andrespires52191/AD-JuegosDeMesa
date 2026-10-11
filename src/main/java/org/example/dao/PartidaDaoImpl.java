package org.example.dao;

import org.example.idao.PartidaDao;
import org.example.models.Partida;

import java.util.List;

public class PartidaDaoImpl implements PartidaDao {
    @Override
    public List<Partida> getPartidas() {
        return null;
    }

    @Override
    public Partida getPartida(int partida_id) {
        return null;
    }

    @Override
    public boolean add(Partida partida) {
        return false;
    }

    @Override
    public boolean update(Partida partida) {
        return false;
    }

    @Override
    public boolean delete(int partida_id) {
        return false;
    }
}
