package org.example.irepositories;

import org.example.models.Socia;

import java.util.List;

public interface SociaRepository {
    List<Socia> getSocias();

    Socia getSocia(int socia_id);

    boolean add(Socia socia);

    boolean update(Socia socia);

    boolean delete(int sociaId);
}
