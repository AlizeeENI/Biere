package fr.exercice.biere.bo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brasserie  {
    private Integer idBrasserie;

    @NotEmpty(message = "il faut un nom")
    private String nom;
    private String adresse;
    private String coordGPS;
    private LocalDate dtOuverture;

}