package org.example.repositories;

import org.example.dao.JuegoDaoImpl;
import org.example.dao.SociaDaoImpl;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        assert socia.getId() > 0;

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

        // limpiar
        assert sociaRepo.delete(socia.getId());
    }
}
