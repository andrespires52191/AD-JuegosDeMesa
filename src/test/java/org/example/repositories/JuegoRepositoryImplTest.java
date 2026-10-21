package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.SociaDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JuegoRepositoryImplTest {
    private JuegoRepositoryImpl juegoRepo = new JuegoRepositoryImpl(new JuegoDaoImpl());
    private SociaRepositoryImpl sociaRepo = new SociaRepositoryImpl(new SociaDaoImpl());

    @Test
    void testCombinado() {
        Socia socia = new Socia(
                "dni",
                "nombre",
                "apellidos",
                LocalDate.parse("1999-01-01"),
                "123456789",
                "email");
        sociaRepo.add(socia);
        assertTrue(socia.getId() > 0);

        Juego juego = new Juego(
                "nombre",
                "autor1, autor2",
                1,
                3,
                10.0,
                "tematica",
                "ubicacion",
                JuegoEstado.DISPONIBLE,
                socia,
                LocalDate.parse("2026-10-01"));

        // comprobar creacion
        assertTrue(juegoRepo.add(juego));
        // comprobar id actualizado
        assertTrue(juego.getId() > 0);
        // volver a comprobar creacion
        juego = juegoRepo.getJuego(juego.getId());
        assertNotNull(juego);

        // comprobar actualizacion
        String nuevosAutores = "autor3";
        juego.setAutores(nuevosAutores);
        assertTrue(juegoRepo.update(juego));
        // volver a comprobar actualizacion
        juego = juegoRepo.getJuego(juego.getId());
        assertEquals(nuevosAutores, juego.getAutores());

        // comprobar borrado
        assertTrue(juegoRepo.delete(juego.getId()));
        // volver comprobar borrado
        assertNull(juegoRepo.getJuego(juego.getId()));

        // limpiar
        assertTrue(sociaRepo.delete(socia.getId()));
    }
}
