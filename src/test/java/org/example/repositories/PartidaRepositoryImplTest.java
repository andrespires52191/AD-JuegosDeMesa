package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.PartidaDaoImpl;
import org.example.dao.SociaDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Partida;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

class PartidaRepositoryImplTest {
    private PartidaRepositoryImpl partidaRepo = new PartidaRepositoryImpl(new PartidaDaoImpl(), new JuegoDaoImpl());
    private JuegoRepositoryImpl juegoRepo = new JuegoRepositoryImpl(new JuegoDaoImpl());
    private SociaRepositoryImpl sociaRepo = new SociaRepositoryImpl(new SociaDaoImpl());

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
        assert juegoRepo.add(juego);
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

        // limpiar
        assert juegoRepo.delete(juego.getId());
    }

    @Test
    void testPartidaJugadoras() {
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
        assert juegoRepo.add(juego);

        Partida partida = new Partida(
                juego,
                "2026-10-02",
                "12:00",
                5
        );
        assert partidaRepo.add(partida);

        Socia socia = new Socia(
                "dni",
                "nombre",
                "apellidos",
                "1999-01-01",
                "123456789",
                "email");
        assert sociaRepo.add(socia);

        // comprobar agregar
        //assert partidaRepo.agregarJugadoraInvitada(partida.getId(), "jugadora1");
        assert partidaRepo.agregarJugadoraSocia(partida.getId(), socia);
        assert partidaRepo.getJugadoras(partida.getId()).size() == 1;
        assert partidaRepo.agregarJugadoraInvitada(partida.getId(), "jugadora2");
        assert partidaRepo.getJugadoras(partida.getId()).size() == 2;

        // comprobar quitar
        //assert partidaRepo.quitarJugadoraInvitada(partida.getId(), "jugadora1");
        assert partidaRepo.quitarJugadoraSocia(partida.getId(), socia);
        assert partidaRepo.getJugadoras(partida.getId()).size() == 1;
        assert partidaRepo.getJugadoras(partida.getId()).get(0).equals("jugadora2");

        // comprobar borrado
        assert partidaRepo.delete(partida.getId());
        assert partidaRepo.getJugadoras(partida.getId()).isEmpty();

        // limpiar
        assert juegoRepo.delete(juego.getId());
        assert sociaRepo.delete(socia.getId());
    }
}
