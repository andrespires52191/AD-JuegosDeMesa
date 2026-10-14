package org.example.dao;

import org.example.idao.PartidaDao;
import org.example.models.Partida;
import org.example.models.Socia;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String query = "update partidas set jugador=?, juego_id=?, fecha=?, hora=?, plazas_deseadas=? where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setString(1, partida.getJu);
            ps.setString(2, partida.getNombre());
            ps.setString(3, partida.getApellidos());
            ps.setString(4, partida.getFechaNac());
            ps.setString(5, partida.getApellidos());
            ps.setString(6, partida.getEmail());

            ps.setInt(7, partida.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean delete(int partida_id) {
        /// NOTA: la tabla relacionada, partidas_jugadoras,
        /// debería borrarse sola por la constraint "on delete cascade"
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

    @Override
    public List<String> getJugadoras(int partida_id) {
        List<String> lista = new ArrayList<>();

        String query = "select jugadora from partidas_jugadoras where partida_id=?";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, partida_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String jugadora = rs.getString(1);
                lista.add(jugadora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean agregarJugadora(int partidaId, String jugadora) {
        String query = "insert into partidas_jugadoras (partida_id, jugadora) values (?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setString(2, jugadora);

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean quitarJugadora(int partidaId, String jugadora) {
        String query = "delete from partidas_jugadoras where partida_id=? and jugarora=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setString(2, jugadora);

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }
}
