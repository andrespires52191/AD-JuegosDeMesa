package org.example.dao;

import org.example.idao.AsociacionDao;
import org.example.models.Asociacion;

public class AsociacionDaoImpl implements AsociacionDao {
    @Override
    public Asociacion getAsociacion() {
        return new Asociacion("cif", "nombre", "razonSocial", "direccion", "fechaApertura");
    }

    @Override
    public boolean update(Asociacion asociacion) {
        return false;
    }
}
