package com.example.gestiondeveloppeur.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int idUser ;

    @Column(name = "NOM")
    private String nomUser;

    @Column(name = "PRENOM")
    private String prenomUser;

    @Column(name = "MAIL")
    private String emailUser;

    @Column(name = "MDP")
    private String mdpUser;

    @Column(name = "ADMIN")
    private boolean adminUser = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Projet> projets ;

    public User() {
    }

    public User(String nomUser, String prenomUser, String emailUser,String mdpUser, boolean adminUser) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.emailUser = emailUser;
        this.mdpUser = mdpUser;
        this.adminUser = adminUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public boolean isAdminUser() {
        return adminUser;
    }

    public void setAdminUser(boolean adminUser) {
        this.adminUser = adminUser;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
}
