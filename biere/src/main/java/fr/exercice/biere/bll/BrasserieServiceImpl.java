package fr.exercice.biere.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.exercice.biere.bo.Brasserie;
import fr.exercice.biere.dal.BrasserieDAO;

@Service
public class BrasserieServiceImpl implements BrasserieService {
    @Autowired
    BrasserieDAO dao;


    @Override
    @Transactional
    public void addBrasserie(Brasserie brasserie) throws BrasserieServiceException {
        dao.insert(brasserie);
    }

    @Override
    public List<Brasserie> getAll() {
        return dao.getAll();
    }

}
