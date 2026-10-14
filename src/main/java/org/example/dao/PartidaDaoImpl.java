package org.example.dao;

import org.example.idao.PartidaDao;
import org.example.models.Partida;
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
        List<Partida> lista = new ArrayList<>();

        String query = "select id, juego_id, fecha, hora, plazas_deseadas from partidas";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partida partida = new Partida(
                        rs.getInt("juego_id"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getInt("plazas_deseadas"));
                partida.setId(rs.getInt("id"));

                lista.add(partida);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Partida getPartida(int partida_id) {
        Partida partida = null;

        String query = "select juego_id, fecha, hora, plazas_deseadas from partidas where id=?";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, partida_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                partida = new Partida(
                        rs.getInt("juego_id"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getInt("plazas_deseadas"));
                partida.setId(partida_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partida;
    }

    @Override
    public boolean add(Partida partida) {
        String query = "insert into partidas (juego_id, fecha, hora, plazas_deseadas) values (?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            // Para recuperar el id se usa Statement.RETURN_GENERATED_KEYS. Más información abajo.
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, partida.getJuego().getId());
            ps.setString(2, partida.getFecha());
            ps.setString(3, partida.getHora());
            ps.setInt(4, partida.getPlazasDeseadas());

            rs = ps.executeUpdate();
            if (rs > 0) {
                // https://dev.mysql.com/doc/connectors/en/connector-j-usagenotes-last-insert-id.html
                ResultSet claves = ps.getGeneratedKeys();
                if (claves.next()) {
                    // poner el id de la fila insertada en el objeto
                    partida.setId(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean update(Partida partida) {
        String query = "update partidas set juego_id=?, fecha=?, hora=?, plazas_deseadas=? where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partida.getJuego().getId());
            ps.setString(2, partida.getFecha());
            ps.setString(3, partida.getHora());
            ps.setInt(4, partida.getPlazasDeseadas());

            ps.setInt(5, partida.getId());

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
        String query = "delete from partidas_jugadoras where partida_id=? and jugadora=?";
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
