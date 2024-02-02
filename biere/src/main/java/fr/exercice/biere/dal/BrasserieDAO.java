package fr.exercice.biere.dal;

import java.util.List;

import fr.exercice.biere.bo.Brasserie;

public interface BrasserieDAO {
    public void insert(Brasserie brasserie);
    public List<Brasserie> getAll();

    Brasserie read(long id);

    // public List<Brasserie> getAllBrasserieByIPA();
}
