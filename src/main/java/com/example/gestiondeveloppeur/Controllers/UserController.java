package com.example.gestiondeveloppeur.Controllers;


import com.example.gestiondeveloppeur.Models.Projet;
import com.example.gestiondeveloppeur.Models.User;
import com.example.gestiondeveloppeur.Repositories.ProjetRepository;
import com.example.gestiondeveloppeur.Repositories.UserRepository;
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
public class UserController {
    @Autowired
    UserRepository userRepository;
    ProjetRepository projetRepository;

    @RequestMapping("/preModifProfil")
    public String preModif(HttpSession session,Model model){
       User user = (User) session.getAttribute("user");
       model.addAttribute("user",user);

       return "ModifProfil";
    }

    @RequestMapping(value = "/postModifProfil", method = RequestMethod.POST)
    public String postModif(@ModelAttribute("user")User user, HttpSession session){

        User user1 = (User) userRepository.findByEmailUser(user.getEmailUser());
        User user2 = (User)session.getAttribute("user");
        System.out.println(user.getEmailUser());
        System.out.println(user2.getEmailUser());

        if(user.getEmailUser().equals(user2.getEmailUser())){
            userRepository.save(user);
            session.removeAttribute("user");
            session.setAttribute("user",user);
            System.out.println("je suis içi");
            return "redirect:/";
        }
        else if(user1 == null){
            userRepository.save(user);
            session.removeAttribute("user");
            session.setAttribute("user",user);
            return "redirect:/";
        }
        else {
            return "dejaInscri";
        }
    }

    @RequestMapping("/listeDeveloppeurs")
    public String listeDev(Model model, Model model1, HttpSession session){
        List<User> users = (List<User>) userRepository.findAllByAdminUser(false);
        model.addAttribute("listeUser",users);
        model1.addAttribute("user", (User) session.getAttribute("user"));
        return "ListeUser";
    }

    @RequestMapping(value = "/preModifDev/{idUser}",method = RequestMethod.GET)
    public String preModif(@PathVariable("idUser") int idUser, Model model){

        User user = (User) userRepository.findByIdUser(idUser);
        model.addAttribute("user",user);

        return "ModifUser";

    }

    @RequestMapping(value = "/postModifDev", method = RequestMethod.POST)
    public String postModif(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/supprimerDev/{idUser}",method = RequestMethod.GET)
    public String suppression(@PathVariable("idUser") int idUser){

        User user = (User) userRepository.findByIdUser(idUser);
        userRepository.delete(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/projetDev/{idUser}",method = RequestMethod.GET)
    public String projetDev(@PathVariable("idUser") int idUser, Model model, HttpSession session, Model model1){

        User user = (User) userRepository.findByIdUser(idUser);
        List<Projet> projets = user.getProjets();
        model.addAttribute("projets", projets);

        User user1 = (User) session.getAttribute("user");
        model1.addAttribute("user",user1);

        return "MesProjets";
    }


}
