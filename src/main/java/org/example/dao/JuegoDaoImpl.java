package org.example.dao;

import org.example.idao.JuegoDao;
import org.example.models.Juego;
import org.example.models.JuegoEstado;
import org.example.models.Socia;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JuegoDaoImpl implements JuegoDao {
    @Override
    public List<Juego> getJuegos() {
        List<Juego> lista = new ArrayList<>();

        String query = "select" +
                " j.id, j.nombre, j.autores, j.min_jugadores, j.max_jugadores, j.estimacion_minutos," +
                " j.tematica, j.ubicacion, j.estado, j.propietaria_id, j.fecha_alta," +
                " s.dni as s_dni, s.nombre as s_nombre, s.apellidos as s_apellidos," +
                " s.fecha_nac as s_fecha_nac, s.telefono as s_telefono, s.email as s_eamil" +
                " from juegos j" +
                " left join socias s on j.propietaria_id = s.id";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Juego juego = null;

                int propietaria_id = rs.getInt("propietaria_id");
                Socia propietaria = null;
                if (propietaria_id > 0) {
                    propietaria = new Socia(
                            rs.getString("s_dni"),
                            rs.getString("s_nombre"),
                            rs.getString("s_apellidos"),
                            rs.getString("s_fecha_nac"),
                            rs.getString("s_telefono"),
                            rs.getString("s_email"));
                    propietaria.setId(propietaria_id);
                }

                juego = new Juego(
                        rs.getString("nombre"),
                        rs.getString("autores"),
                        rs.getInt("min_jugadores"),
                        rs.getInt("max_jugadores"),
                        rs.getInt("estimacion_minutos"),
                        rs.getString("tematica"),
                        rs.getString("ubicacion"),
                        JuegoEstado.valueOf(rs.getString("estado")),
                        propietaria,
                        rs.getString("fecha_alta")
                );

                juego.setId(rs.getInt("id"));

                lista.add(juego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Juego getJuego(int juego_id) {
        Juego juego = null;

        String query = "select" +
                " j.nombre, j.autores, j.min_jugadores, j.max_jugadores, j.estimacion_minutos," +
                " j.tematica, j.ubicacion, j.estado, j.propietaria_id, j.fecha_alta," +
                " s.dni as s_dni, s.nombre as s_nombre, s.apellidos as s_apellidos," +
                " s.fecha_nac as s_fecha_nac, s.telefono as s_telefono, s.email as s_email" +
                " from juegos j" +
                " left join socias s on j.propietaria_id = s.id" +
                " where j.id=?";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, juego_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int propietaria_id = rs.getInt("propietaria_id");
                Socia propietaria = null;
                if (propietaria_id > 0) {
                    propietaria = new Socia(
                            rs.getString("s_dni"),
                            rs.getString("s_nombre"),
                            rs.getString("s_apellidos"),
                            rs.getString("s_fecha_nac"),
                            rs.getString("s_telefono"),
                            rs.getString("s_email"));
                    propietaria.setId(propietaria_id);
                }

                juego = new Juego(
                        rs.getString("nombre"),
                        rs.getString("autores"),
                        rs.getInt("min_jugadores"),
                        rs.getInt("max_jugadores"),
                        rs.getInt("estimacion_minutos"),
                        rs.getString("tematica"),
                        rs.getString("ubicacion"),
                        JuegoEstado.valueOf(rs.getString("estado")),
                        propietaria,
                        rs.getString("fecha_alta")
                );

                juego.setId(juego_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return juego;
    }

    @Override
    public boolean add(Juego juego) {
        String query = "insert into juegos" +
                " (nombre, autores, min_jugadores, max_jugadores, estimacion_minutos," +
                " tematica, ubicacion, estado, propietaria_id, fecha_alta)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            // Para recuperar el id se usa Statement.RETURN_GENERATED_KEYS. Más información abajo.
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Socia propietaria = juego.getPropietaria();

            ps.setString(1, juego.getNombre());
            ps.setString(2, juego.getAutores());
            ps.setInt(3, juego.getMinJugadores());
            ps.setInt(4, juego.getMaxJugadores());
            ps.setDouble(5, juego.getEstimacionMinutos());
            ps.setString(6, juego.getTematica());
            ps.setString(7, juego.getUbicacion());
            ps.setString(8, juego.getEstado().toString());
            if (propietaria == null) {
                ps.setNull(9, java.sql.Types.INTEGER);
            } else {
                ps.setInt(9, propietaria.getId());
            }
            ps.setString(10, juego.getFechaAlta());

            rs = ps.executeUpdate();
            if (rs > 0) {
                // https://dev.mysql.com/doc/connectors/en/connector-j-usagenotes-last-insert-id.html
                ResultSet claves = ps.getGeneratedKeys();
                if (claves.next()) {
                    // poner el id de la fila insertada en el objeto
                    juego.setId(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean update(Juego juego) {
        String query = "update juegos set" +
                " nombre=?, autores=?, min_jugadores=?, max_jugadores=?, estimacion_minutos=?," +
                " tematica=?, ubicacion=?, estado=?, propietaria_id=?, fecha_alta=?" +
                " where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            Socia propietaria = juego.getPropietaria();

            ps.setString(1, juego.getNombre());
            ps.setString(2, juego.getAutores());
            ps.setInt(3, juego.getMinJugadores());
            ps.setInt(4, juego.getMaxJugadores());
            ps.setDouble(5, juego.getEstimacionMinutos());
            ps.setString(6, juego.getTematica());
            ps.setString(7, juego.getUbicacion());
            ps.setString(8, juego.getEstado().toString());
            if (propietaria == null) {
                ps.setNull(9, java.sql.Types.INTEGER);
            } else {
                ps.setInt(9, propietaria.getId());
            }
            ps.setString(10, juego.getFechaAlta());

            ps.setInt(11, juego.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean delete(int juego_id) {
        String query = "delete from juegos where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, juego_id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }
}
