package org.example.idao;

import org.example.models.Asociacion;

public interface AsociacionDao {
    Asociacion getAsociacion();

    boolean update(Asociacion asociacion);
}
