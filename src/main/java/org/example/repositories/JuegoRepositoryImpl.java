package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.irepositories.JuegoRepository;
import org.example.models.Juego;

import java.util.List;

public class JuegoRepositoryImpl implements JuegoRepository {
    private final JuegoDaoImpl juegoDao;

    public JuegoRepositoryImpl(JuegoDaoImpl juegoDao) {
        this.juegoDao = juegoDao;
    }

    @Override
    public List<Juego> getJuegos() {
        return null;
    }

    @Override
    public Juego getJuego(int juegoId) {
        return null;
    }

    @Override
    public boolean add(Juego juego) {
        return false;
    }

    @Override
    public boolean update(Juego juego) {
        return false;
    }

    @Override
    public boolean delete(int juegoId) {
        return false;
    }
}
