/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author HP
 */
public class Fournisseur {
    private int id;
    private String nom_fournisseur;
    private String adresse_fournisseur;
    private String MDP_fournisseur;
    private String lastname;
    private String tel;

    public Fournisseur() {
    }

    public Fournisseur(int id) {
        this.id = id;
    }
    
    
    
//premier constructeur avec tous les parametre
    public Fournisseur(int id, String nom_fournisseur, String adresse_fournisseur, String MDP_fournisseur, String lastname, String tel) {
        this.id = id;
        this.nom_fournisseur = nom_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
        this.MDP_fournisseur = MDP_fournisseur;
        this.lastname = lastname;
        this.tel = tel;
    }
    
    //deuxiéme constructeur avec tous les parametre sauf id
    public Fournisseur(String nom_fournisseur, String adresse_fournisseur, String MDP_fournisseur, String lastname, String tel) {
        this.nom_fournisseur = nom_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
        this.MDP_fournisseur = MDP_fournisseur;
        this.lastname = lastname;
        this.tel = tel;
    }
    //getter et setter de tous les paramétre
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public String getAdresse_fournisseur() {
        return adresse_fournisseur;
    }

    public void setAdresse_fournisseur(String adresse_fournisseur) {
        this.adresse_fournisseur = adresse_fournisseur;
    }

    public String getMDP_fournisseur() {
        return MDP_fournisseur;
    }

    public void setMDP_fournisseur(String MDP_fournisseur) {
        this.MDP_fournisseur = MDP_fournisseur;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    //methode toString avec tous les parametre

    /*@Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom_fournisseur=" + nom_fournisseur + ", adresse_fournisseur=" + adresse_fournisseur + ", MDP_fournisseur=" + MDP_fournisseur + ", lastname=" + lastname + ", tel=" + tel + '}';
    }*/
/*
    @Override
    public String toString() {
        return "Fournisseur{" + "nom_fournisseur=" + nom_fournisseur + '}';
    }*/

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom_fournisseur=" + nom_fournisseur + ", adresse_fournisseur=" + adresse_fournisseur + ", MDP_fournisseur=" + MDP_fournisseur + ", lastname=" + lastname + ", tel=" + tel + '}';
    }
    
    
    
            
}
