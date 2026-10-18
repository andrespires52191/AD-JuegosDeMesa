package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.PartidaDaoImpl;
import org.example.irepositories.PartidaRepository;
import org.example.models.Partida;

import java.util.List;

public class PartidaRepositoryImpl implements PartidaRepository {
    private final PartidaDaoImpl partidaDao;
    private final JuegoDaoImpl juegoDao;

    public PartidaRepositoryImpl(PartidaDaoImpl partidaDao, JuegoDaoImpl juegoDao) {
        this.partidaDao = partidaDao;
        this.juegoDao = juegoDao;
    }

    @Override
    public List<Partida> getPartidas() {
        List<Partida> lista = partidaDao.getPartidas();

        for (Partida partida : lista) {
            int juegoId = partida.getJuego().getId();
            partida.setJuego(juegoDao.getJuego(juegoId));
            partida.setJugadoras(partidaDao.getJugadoras(partida.getId()));
        }

        return lista;
    }

    @Override
    public Partida getPartida(int partidaId) {
        Partida partida = partidaDao.getPartida(partidaId);

        if (partida != null) {
            int juegoId = partida.getJuego().getId();
            partida.setJuego(juegoDao.getJuego(juegoId));
            partida.setJugadoras(partidaDao.getJugadoras(partidaId));
        }

        return partida;
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
    public List<String> getJugadoras(int partidaId) {
        return partidaDao.getJugadoras(partidaId);
    }

    @Override
    public boolean agregarJugadora(int partidaId, String jugadora) {
        return partidaDao.agregarJugadora(partidaId, jugadora);
    }

    @Override
    public boolean quitarJugadora(int partidaId, String jugadora) {
        return partidaDao.quitarJugadora(partidaId, jugadora);
    }
}
