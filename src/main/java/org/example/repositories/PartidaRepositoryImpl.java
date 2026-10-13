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
        return partidaDao.getPartidas();
    }

    @Override
    public Partida getPartida(int partidaId) {
        return partidaDao.getPartida(partidaId);
    }

    @Override
    public boolean add(Partida partida) {
        return partidaDao.add(partida);
    }

    @Override
    public boolean update(Partida partida) {
        return partidaDao.update(partida);
    }

    @Override
    public boolean delete(int partidaId) {
        return partidaDao.delete(partidaId);
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
