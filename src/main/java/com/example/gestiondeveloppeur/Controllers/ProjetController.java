package com.example.gestiondeveloppeur.Controllers;


import com.example.gestiondeveloppeur.Models.Projet;
import com.example.gestiondeveloppeur.Models.User;
import com.example.gestiondeveloppeur.Repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    @RequestMapping("/listeProjets")
    public String mesProjets(Model model, HttpSession session, Model model1){
        User user = (User) session.getAttribute("user");

        List<Projet> projets = projetRepository.findAllByUser(user);
        model.addAttribute("projets",projets);
        model1.addAttribute("user",user);
        System.out.println(user.getNomUser());
        return ("MesProjets");
    }


    @RequestMapping(value = "/preModifProjet/{idProjet}",method = RequestMethod.GET)
    public String preModif(@PathVariable("idProjet") int idProjet, Model model){

        Projet projet = (Projet) projetRepository.findByIdProjet(idProjet);
        model.addAttribute("projet",projet);

        return "ModifProjet";

    }

    @RequestMapping(value = "/postModifProjet", method = RequestMethod.POST)
    public String postModif(@ModelAttribute("projet") Projet projet, HttpSession session){
        User user = (User) session.getAttribute("user");
        projet.setUserProjet(user);
        projetRepository.save(projet);
        return "redirect:/";
    }

    @RequestMapping(value = "/supprimerProjet/{idProjet}",method = RequestMethod.GET)
    public String suppression(@PathVariable("idProjet") int idProjet){

       Projet projet = (Projet) projetRepository.findByIdProjet(idProjet);
       projetRepository.delete(projet);

       return "redirect:/";
    }

    @RequestMapping("/preAjoutProjet")
    public String preAjoutProjet(Model model){
        Projet projet = new Projet();
        model.addAttribute("projet",projet);
        return "AjoutProjet";
    }

    @RequestMapping(value = "/postAjoutProjet", method = RequestMethod.POST)
    public String postAjoutProjet(@ModelAttribute("projet") Projet projet,HttpSession session){
        projet.setUserProjet((User) session.getAttribute("user"));
        projetRepository.save(projet);

        return ("redirect:/");
    }

}
