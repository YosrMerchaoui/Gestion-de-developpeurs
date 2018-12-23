package com.example.gestiondeveloppeur.Repositories;

import com.example.gestiondeveloppeur.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer>{
    public User findByEmailUser(String mail);
    public  User findUserByEmailUserAndMdpUser(String mail, String mdp);
    public  User findByIdUser(int id);
    public List<User> findAllByAdminUser(boolean admin);
}
