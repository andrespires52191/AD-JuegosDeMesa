package org.example.repositories;

import org.example.dao.PartidaDaoImpl;
import org.example.irepositories.PartidaRepository;
import org.example.models.Partida;

import java.util.List;

public class PartidaRepositoryImpl implements PartidaRepository {
    private final PartidaDaoImpl partidaDao;

    public PartidaRepositoryImpl(PartidaDaoImpl partidaDao) {
        this.partidaDao = partidaDao;
    }

    @Override
    public List<Partida> getPartidas() {
        return null;
    }

    @Override
    public Partida getPartida(int partidaId) {
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
    public boolean delete(int partidaId) {
        return false;
    }

    @Override
    public boolean agregarJugador(String jugador) {
        return false;
    }

    @Override
    public boolean quitarJugador(String jugador) {
        return false;
    }
}
