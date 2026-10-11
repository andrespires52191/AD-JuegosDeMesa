package org.example.idao;

import org.example.models.Socia;

import java.util.List;

public interface SociaDao {
    List<Socia> getSocias();

    Socia getSocia(int socia_id);

    boolean add(Socia socia);

    boolean update(Socia socia);

    boolean delete(int socia_id);
}
