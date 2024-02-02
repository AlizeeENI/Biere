package fr.exercice.biere.bo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Biere {
    private Integer idBiere;

    @NotEmpty(message = "il faut un nom")
    private String nom;
    private String tyBiere;
    private String description;
    private Integer dgAlcool;
    private Integer note;

    private Brasserie brasserie;

    public Biere(Integer idBiere, String nom, String tyBiere, String description, Integer dgAlcool, Integer note) {
        this.idBiere = idBiere;
        this.nom = nom;
        this.tyBiere = tyBiere;
        this.description = description;
        this.dgAlcool = dgAlcool;
        this.note = note;
    }
}