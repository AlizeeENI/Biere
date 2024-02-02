package fr.exercice.biere.bll;

import java.util.List;

import fr.exercice.biere.bo.Biere;

public interface BiereService {
    /**
     * Ajoute une bière
     */
    public void addBiere(Biere bierre) throws BiereServiceException;

    /**
     * Liste toutes les bières
     */
    public List<Biere> getAll();

    /**
     * Liste les bières
     * CT001 d'une même brasserie
     */
    List<Biere> getAllBearBrasserie(long id) throws BiereServiceException;
}

