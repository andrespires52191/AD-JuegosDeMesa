package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.PartidaDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Partida;
import org.junit.jupiter.api.Test;

class PartidaRepositoryImplTest {
    private PartidaRepositoryImpl partidaRepo = new PartidaRepositoryImpl(new PartidaDaoImpl(), new JuegoDaoImpl());
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
        juegoRepo.add(juego);
        assert juego.getId() > 0;

        Partida partida = new Partida(
                juego,
                "2026-10-02",
                "12:00",
                5
        );

        // comprobar creacion
        assert partidaRepo.add(partida);
        // comprobar id actualizado
        assert partida.getId() > 0;
        // volver a comprobar creacion
        partida = partidaRepo.getPartida(partida.getId());
        assert partida != null;

        // comprobar actualizacion
        String nuevaHora = "14:00";
        partida.setHora(nuevaHora);
        assert partidaRepo.update(partida);
        // volver a comprobar actualizacion
        partida = partidaRepo.getPartida(partida.getId());
        assert partida.getHora().equals(nuevaHora);

        // comprobar borrado
        assert partidaRepo.delete(partida.getId());
        // volver comprobar borrado
        partida = partidaRepo.getPartida(partida.getId());
        assert partida == null;
    }
}
