package com.example.gestiondeveloppeur.Models;
import javax.persistence.*;

@Entity
@Table(name = "PROJET")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int idProjet;

    @Column(name = "NOM")
    private String nomProjet;

    @Column(name = "DESCRIPTION")
    private String descriptionProjet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Projet(String nomProjet, String descriptionProjet) {
        this.nomProjet = nomProjet;
        this.descriptionProjet = descriptionProjet;
    }

    public Projet(){}

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public User getUserProjet() {
        return user;
    }

    public void setUserProjet(User userProjet) {
        this.user = userProjet;
    }
}
