package org.example.irepositories;

import org.example.models.Juego;

import java.util.List;

public interface JuegoRepository {
    List<Juego> getJuegos();

    Juego getJuego(int juegoId);

    boolean add(Juego juego);

    boolean update(Juego juego);

    boolean delete(int juegoId);
}
