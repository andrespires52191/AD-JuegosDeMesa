package org.example.dao;

import org.example.idao.PartidaDao;
import org.example.models.Juego;
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
        List<Partida> lista = new ArrayList<>();

        String query = "select id, juego_id, fecha, hora, plazas_deseadas from partidas";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Juego juego = new Juego(rs.getInt("juego_id"));

                Partida partida = new Partida(
                        juego,
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("plazas_deseadas"));
                partida.setId(rs.getInt("id"));

                lista.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
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
                        new Juego(rs.getInt("juego_id")),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
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

            Juego juego = partida.getJuego();
            if (juego == null) {
                System.out.println("Referencia a juego incorrecta.");
                return false;
            } else {
                ps.setInt(1, juego.getId());
            }
            ps.setDate(2, java.sql.Date.valueOf(partida.getFecha()));
            ps.setTime(3, java.sql.Time.valueOf(partida.getHora()));
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
        if (partida == null) {
            System.out.println("partida null");
            return false;
        }

        String query = "update partidas set juego_id=?, fecha=?, hora=?, plazas_deseadas=? where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            Juego juego = partida.getJuego();
            if (juego == null) {
                System.out.println("Referencia a juego incorrecta.");
                return false;
            } else {
                ps.setInt(1, juego.getId());
            }
            ps.setDate(2, java.sql.Date.valueOf(partida.getFecha()));
            ps.setTime(3, java.sql.Time.valueOf(partida.getHora()));
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

        String query = "select jugadora_id, nombre_invitada from partidas_jugadoras where partida_id=?";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, partida_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String jugadora;
                int jugadora_id = rs.getInt("jugadora_id");
                if (jugadora_id > 0) {
                    jugadora = "Socia #" + jugadora_id;
                } else {
                    jugadora = rs.getString("nombre_invitada");
                }
                lista.add(jugadora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean agregarJugadoraInvitada(int partidaId, String invitada) {
        String query = "insert into partidas_jugadoras (partida_id, nombre_invitada) values (?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setString(2, invitada);

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean agregarJugadoraSocia(int partidaId, Socia socia) {
        String query = "insert into partidas_jugadoras (partida_id, jugadora_id) values (?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setInt(2, socia.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean quitarJugadoraInvitada(int partidaId, String invitada) {
        String query = "delete from partidas_jugadoras where partida_id=? and nombre_invitada=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setString(2, invitada);

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean quitarJugadoraSocia(int partidaId, Socia socia) {
        String query = "delete from partidas_jugadoras where partida_id=? and jugadora_id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, partidaId);
            ps.setInt(2, socia.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public List<Partida> partidasDeSocia(Socia socia) {
        List<Partida> lista = new ArrayList<>();

        String query = "select p.id, p.juego_id, p.fecha, p.hora, p.plazas_deseadas" +
                " from partidas as p" +
                " join ( select partida_id from partidas_jugadoras where jugadora_id = ? )" +
                " as j" +
                " on j.partida_id = p.id";

        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, socia.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Juego juego = new Juego(rs.getInt("juego_id"));

                Partida partida = new Partida(
                        juego,
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("plazas_deseadas"));
                partida.setId(rs.getInt("id"));

                lista.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
