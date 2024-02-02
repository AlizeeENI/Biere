package fr.exercice.biere.ihm;

import java.util.List;

import fr.exercice.biere.bo.Brasserie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import fr.exercice.biere.bll.BiereService;
import fr.exercice.biere.bll.BiereServiceException;
import fr.exercice.biere.bo.Biere;
import fr.exercice.biere.bll.BrasserieService;

@Controller
@RequestMapping("/biere")
public class BiereController {
    @Autowired
    BiereService service;

    @Autowired
    BrasserieService brasserieService;

    @ModelAttribute("lstBieres")
    private List<Biere> getListBieres(){
        return service.getAll();
    }

    @ModelAttribute("lstBrasseries")
    private List<Brasserie> getListBrasserie(){return brasserieService.getAll();}

    @GetMapping
    public String init(Biere biere) {
        return "biere";
    }

    @PostMapping
    public String valid(@Valid Biere biere, BindingResult errors, @RequestParam("idBrasserie")Integer idBrasserie, Model model) {
        if(errors.hasErrors()) {
            return "biere";
        }
        try {
            biere.setBrasserie(getListBrasserie().stream()
                    .filter(m -> m.getIdBrasserie()==idBrasserie )
                    .findAny().orElse(null));
            System.out.println(biere);
            service.addBiere(biere);
        } catch (BiereServiceException e) {
            // TODO gérer l'exception
            e.printStackTrace();
        }
        return "redirect:/biere";
    }
}
