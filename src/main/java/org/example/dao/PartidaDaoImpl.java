package org.example.dao;

import org.example.idao.PartidaDao;
import org.example.models.Partida;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PartidaDaoImpl implements PartidaDao {
    @Override
    public List<Partida> getPartidas() {
        return null;
    }

    @Override
    public Partida getPartida(int partida_id) {
        return null;
    }

    @Override
    public boolean add(Partida partida) {
        return false;
    }

    @Override
    public boolean update(Partida partida) {
        return false;
    }

    @Override
    public boolean delete(int partida_id) {
        String query = "delete from partidas where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, partida_id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }
}
