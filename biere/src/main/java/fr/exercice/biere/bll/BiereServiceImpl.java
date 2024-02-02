package fr.exercice.biere.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.exercice.biere.bo.Biere;
import fr.exercice.biere.dal.BiereDAO;

@Service
public class BiereServiceImpl implements BiereService {
    @Autowired
    BiereDAO dao;


    @Override
    @Transactional
    public void addBiere(Biere biere) throws BiereServiceException {
        dao.insert(biere);
    }

    @Override
    public List<Biere> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Biere> getAllBearBrasserie(long id) throws BiereServiceException {
        return this.dao.getAllBearBrasserie(id);
    }
}
