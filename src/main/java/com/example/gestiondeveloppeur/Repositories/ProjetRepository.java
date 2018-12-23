package com.example.gestiondeveloppeur.Repositories;

import com.example.gestiondeveloppeur.Models.Projet;
import com.example.gestiondeveloppeur.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjetRepository extends CrudRepository<Projet,Integer>{

    public List<Projet> findAllByUser(User user);
    public Projet findByIdProjet(int id);
}
