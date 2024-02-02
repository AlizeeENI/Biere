package fr.exercice.biere.dal;

import java.util.List;

import fr.exercice.biere.bo.Biere;

public interface BiereDAO {
    public void insert(Biere biere);
    public List<Biere> getAll();
    public List<Biere> getAllBearBrasserie(long id);
}
