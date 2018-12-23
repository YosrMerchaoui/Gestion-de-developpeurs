package com.example.gestiondeveloppeur.Controllers;

import com.example.gestiondeveloppeur.Models.User;
import com.example.gestiondeveloppeur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class InscriController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/inscri")
    public String preFormInscri(Model model)
    {
        User user= new User();
        model.addAttribute("user",user);
        return"inscription";
    }

    @RequestMapping(value = "/inscriForm", method= RequestMethod.POST)
    public String postFormInscri(@ModelAttribute("user") User user, HttpSession session){
        User user1 = userRepository.findByEmailUser(user.getEmailUser());
        if(user1==null){
        userRepository.save(user);
        session.setAttribute("user",user);
        }
        else { return "/dejaInscri";}
        return "redirect:/";

    }



}
