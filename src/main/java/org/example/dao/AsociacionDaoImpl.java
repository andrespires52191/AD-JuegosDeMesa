package org.example.dao;

import org.example.idao.AsociacionDao;
import org.example.models.Asociacion;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsociacionDaoImpl implements AsociacionDao {
    @Override
    public Asociacion getAsociacion() {
        // siempre id=1, solo hay una fila
        String query = "select cif, nombre, razon_social, direccion, fecha_apertura from asociacion where id=1";
        PreparedStatement ps = null;
        Asociacion asociacion = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                asociacion = new Asociacion(
                        rs.getString("cif"),
                        rs.getString("nombre"),
                        rs.getString("razon_social"),
                        rs.getString("direccion"),
                        rs.getString("fecha_apertura"));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
        return asociacion;
    }

    @Override
    public boolean update(Asociacion asociacion) {
        String query = "update asociacion set cif=?, nombre=?, razon_social=?, direccion=?, fecha_apertura=? where id=1 ";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setString(1, asociacion.getCif());
            ps.setString(2, asociacion.getNombre());
            ps.setString(3, asociacion.getRazonSocial());
            ps.setString(4, asociacion.getDireccion());
            ps.setString(5, asociacion.getFechaApertura());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }
}