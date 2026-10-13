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
        return sociaDao.getSocias();
    }

    @Override
    public Socia getSocia(int socia_id) {
        return sociaDao.getSocia(socia_id);
    }

    @Override
    public boolean add(Socia socia) {
        return sociaDao.add(socia);
    }

    @Override
    public boolean update(Socia socia) {
        return sociaDao.update(socia);
    }

    @Override
    public boolean delete(int sociaId) {
        return sociaDao.delete(sociaId);
    }
}
