package org.example.dao;

import org.example.idao.SociaDao;
import org.example.models.Socia;
import org.example.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SociaDaoImpl implements SociaDao {
    @Override
    public List<Socia> getSocias() {
        List<Socia> lista = new ArrayList<>();

        String query = "select id, dni, nombre, apellidos, fecha_nac, telefono, email from socias";
        PreparedStatement ps = null;
        Socia socia = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                socia = new Socia(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nac"),
                        rs.getString("telefono"),
                        rs.getString("email"));
                socia.setId(rs.getInt("id"));

                lista.add(socia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Socia getSocia(int socia_id) {
        Socia socia = null;

        String query = "select dni, nombre, apellidos, fecha_nac, telefono, email from socias where id=?";
        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, socia_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                socia = new Socia(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nac"),
                        rs.getString("telefono"),
                        rs.getString("email"));
                socia.setId(socia_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socia;
    }

    @Override
    public boolean add(Socia socia) {
        String query = "insert into socias (dni, nombre, apellidos, fecha_nac, telefono, email) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int rs = 0;
        try {
            // Para recuperar el id se usa Statement.RETURN_GENERATED_KEYS. Más información abajo.
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, socia.getDni());
            ps.setString(2, socia.getNombre());
            ps.setString(3, socia.getApellidos());
            ps.setString(4, socia.getFechaNac());
            ps.setString(5, socia.getTelefono());
            ps.setString(6, socia.getEmail());

            rs = ps.executeUpdate();
            if (rs > 0) {
                // https://dev.mysql.com/doc/connectors/en/connector-j-usagenotes-last-insert-id.html
                ResultSet claves = ps.getGeneratedKeys();
                if (claves.next()) {
                    // poner el id de la fila insertada en el objeto
                    socia.setId(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean update(Socia socia) {
        String query = "update socias set dni=?, nombre=?, apellidos=?, fecha_nac=?, telefono=?, email=? where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

            ps.setString(1, socia.getDni());
            ps.setString(2, socia.getNombre());
            ps.setString(3, socia.getApellidos());
            ps.setString(4, socia.getFechaNac());
            ps.setString(5, socia.getApellidos());
            ps.setString(6, socia.getEmail());

            ps.setInt(7, socia.getId());

            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }

    @Override
    public boolean delete(int socia_id) {
        String query = "delete from socias where id=?";
        PreparedStatement ps;
        int rs = 0;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, socia_id);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs > 0);
    }
}
