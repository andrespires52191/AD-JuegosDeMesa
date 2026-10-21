package org.example.repositories;

import org.example.dao.SociaDaoImpl;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SociaRepositoryImplTest {
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

        // comprobar creacion
        assertTrue(sociaRepo.add(socia));
        // comprobar id actualizado
        assertTrue(socia.getId() > 0);
        // volver a comprobar creacion
        socia = sociaRepo.getSocia(socia.getId());
        assertNotNull(socia);

        // comprobar actualizacion
        String nuevoTelefono = "987654321";
        socia.setTelefono(nuevoTelefono);
        assertTrue(sociaRepo.update(socia));
        // volver a comprobar actualizacion
        socia = sociaRepo.getSocia(socia.getId());
        assertEquals(nuevoTelefono, socia.getTelefono());

        // comprobar borrado
        assertTrue(sociaRepo.delete(socia.getId()));
        // volver comprobar borrado
        assertNull(sociaRepo.getSocia(socia.getId()));
    }
}
