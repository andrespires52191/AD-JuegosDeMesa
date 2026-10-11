package org.example.idao;

import org.example.models.Juego;

import java.util.List;

public interface JuegoDao {
    List<Juego> getJuegos();

    Juego getJuego(int juego_id);

    boolean add(Juego juego);

    boolean update(Juego juego);

    boolean delete(int juego_id);
}
