package org.example.repositories;

import org.example.dao.SociaDaoImpl;
import org.example.irepositories.SociaRepository;
import org.example.models.Socia;

import java.util.List;

public class SociaRepositoryImpl implements SociaRepository {
    private final SociaDaoImpl sociaDao;

    public SociaRepositoryImpl(SociaDaoImpl sociaDao) {
        this.sociaDao = sociaDao;
    }

    @Override
    public List<Socia> getSocias() {
        return null;
    }

    @Override
    public Socia getSocia(int socia_id) {
        return null;
    }

    @Override
    public boolean add(Socia socia) {
        return false;
    }

    @Override
    public boolean update(Socia socia) {
        return false;
    }

    @Override
    public boolean delete(int sociaId) {
        return false;
    }
}
