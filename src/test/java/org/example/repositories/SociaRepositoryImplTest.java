package org.example.repositories;

import org.example.dao.SociaDaoImpl;
import org.example.models.Socia;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        assert sociaRepo.add(socia);
        // comprobar id actualizado
        assert socia.getId() > 0;
        // volver a comprobar creacion
        socia = sociaRepo.getSocia(socia.getId());
        assert socia != null;

        // comprobar actualizacion
        String nuevoTelefono = "987654321";
        socia.setTelefono(nuevoTelefono);
        assert sociaRepo.update(socia);
        // volver a comprobar actualizacion
        socia = sociaRepo.getSocia(socia.getId());
        assert socia.getTelefono().equals(nuevoTelefono);

        // comprobar borrado
        assert sociaRepo.delete(socia.getId());
        // volver comprobar borrado
        assert sociaRepo.getSocia(socia.getId()) == null;
    }
}
