package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.PartidaDaoImpl;
import org.example.dao.SociaDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Partida;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

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
                LocalDate.parse("2026-10-01"));
        assertTrue(juegoRepo.add(juego));
        assertTrue(juego.getId() > 0);

        Partida partida = new Partida(
                juego,
                LocalDate.parse("2026-10-02"),
                LocalTime.parse("12:00"),
                5
        );

        // comprobar creacion
        assertTrue(partidaRepo.add(partida));
        // comprobar id actualizado
        assertTrue(partida.getId() > 0);
        // volver a comprobar creacion
        partida = partidaRepo.getPartida(partida.getId());
        assertNotNull(partida);

        // comprobar actualizacion
        LocalTime nuevaHora = LocalTime.parse("14:00");
        partida.setHora(nuevaHora);
        assertTrue(partidaRepo.update(partida));
        // volver a comprobar actualizacion
        partida = partidaRepo.getPartida(partida.getId());
        assertEquals(partida.getHora(), nuevaHora);

        // comprobar borrado
        assertTrue(partidaRepo.delete(partida.getId()));
        // volver comprobar borrado
        partida = partidaRepo.getPartida(partida.getId());
        assertNull(partida);

        // limpiar
        assertTrue(juegoRepo.delete(juego.getId()));
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
                LocalDate.parse("2026-10-01"));
        assertTrue(juegoRepo.add(juego));

        Partida partida = new Partida(
                juego,
                LocalDate.parse("2026-10-02"),
                LocalTime.parse("12:00"),
                5
        );
        assertTrue(partidaRepo.add(partida));

        Socia socia = new Socia(
                "dni",
                "nombre",
                "apellidos",
                LocalDate.parse("1999-01-01"),
                "123456789",
                "email");
        assertTrue(sociaRepo.add(socia));

        // comprobar agregar
        //assertTrue(partidaRepo.agregarJugadoraInvitada(partida.getId(), "jugadora1"));
        assertTrue(partidaRepo.agregarJugadoraSocia(partida.getId(), socia));
        assertEquals(1, partidaRepo.getJugadoras(partida.getId()).size());
        assertTrue(partidaRepo.agregarJugadoraInvitada(partida.getId(), "jugadora2"));
        assertEquals(2, partidaRepo.getJugadoras(partida.getId()).size());

        // comprobar quitar
        //assertTrue(partidaRepo.quitarJugadoraInvitada(partida.getId(), "jugadora1"));
        assertTrue(partidaRepo.quitarJugadoraSocia(partida.getId(), socia));
        assertEquals(1, partidaRepo.getJugadoras(partida.getId()).size());
        assertEquals("jugadora2", partidaRepo.getJugadoras(partida.getId()).get(0));

        // comprobar borrado
        assertTrue(partidaRepo.delete(partida.getId()));
        assertTrue(partidaRepo.getJugadoras(partida.getId()).isEmpty());

        // limpiar
        assertTrue(juegoRepo.delete(juego.getId()));
        assertTrue(sociaRepo.delete(socia.getId()));
    }
}
