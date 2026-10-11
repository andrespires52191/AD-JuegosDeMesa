package org.example.dao;

import org.example.idao.JuegoDao;
import org.example.models.Juego;

import java.util.List;

public class JuegoDaoImpl implements JuegoDao {
    @Override
    public List<Juego> getJuegos() {
        return null;
    }

    @Override
    public Juego getJuego(int juego_id) {
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
    public boolean delete(int juego_id) {
        return false;
    }
}
