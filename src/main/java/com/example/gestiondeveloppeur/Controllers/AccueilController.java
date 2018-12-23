package com.example.gestiondeveloppeur.Controllers;
import com.example.gestiondeveloppeur.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccueilController {

    @RequestMapping("/")
    public  String accueil(Model model, HttpSession session){
        User user =(User) session.getAttribute("user");

        model.addAttribute("user",user);

        return"Accueil";
    }

    @RequestMapping("/deconnexion")
    public String deconnexion(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

}
