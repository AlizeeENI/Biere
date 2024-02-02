package fr.exercice.biere.bll;

import java.util.List;

import fr.exercice.biere.bo.Brasserie;

public interface BrasserieService {
    /**
     * Ajoute une brasserie
     */
    public void addBrasserie(Brasserie brasserie) throws BrasserieServiceException;

    /**
     * Liste toutes les brasseries
     */
    public List<Brasserie> getAll();
}

