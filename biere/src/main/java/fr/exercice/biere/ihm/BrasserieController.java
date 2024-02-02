package fr.exercice.biere.ihm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;


import fr.exercice.biere.bll.BrasserieService;
import fr.exercice.biere.bll.BrasserieServiceException;
import fr.exercice.biere.bo.Brasserie;

@Controller
@RequestMapping("/brasserie")
public class BrasserieController {
    @Autowired
    BrasserieService service;

    @ModelAttribute("lstBrasseries")
    private List<Brasserie> getListBrasseries(){
        return service.getAll();
    }

    @GetMapping
    public String init(Brasserie brasserie) {
        return "brasserie";
    }

    @PostMapping
    public String valid(@Valid Brasserie brasserie, BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            return "brasserie";
        }

        try {
            service.addBrasserie(brasserie);
        } catch (BrasserieServiceException e) {
            // TODO gérer l'exception
            e.printStackTrace();
        }
        return "redirect:/brasserie";
    }
}
