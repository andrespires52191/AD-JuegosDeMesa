package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.junit.jupiter.api.Test;

class JuegoRepositoryImplTest {
    private JuegoRepositoryImpl juegoRepo = new JuegoRepositoryImpl(new JuegoDaoImpl());

    @Test
    void testCombinado() {
        Juego juego = new Juego(
                "nombre",
                "autor1, autor2",
                1,
                3,
                10.0,
                "tematica",
                "ubicacion",
                JuegoEstado.DISPONIBLE,
                null,
                "2026-10-01");

        // comprobar creacion
        assert juegoRepo.add(juego);
        // comprobar id actualizado
        assert juego.getId() > 0;
        // volver a comprobar creacion
        juego = juegoRepo.getJuego(juego.getId());
        assert juego != null;

        // comprobar actualizacion
        String nuevosAutores = "autor3";
        juego.setAutores(nuevosAutores);
        assert juegoRepo.update(juego);
        // volver a comprobar actualizacion
        juego = juegoRepo.getJuego(juego.getId());
        assert juego.getAutores().equals(nuevosAutores);

        // comprobar borrado
        assert juegoRepo.delete(juego.getId());
        // volver comprobar borrado
        assert juegoRepo.getJuego(juego.getId()) == null;
    }
}
