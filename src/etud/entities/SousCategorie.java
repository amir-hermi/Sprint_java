/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.entities;

/**
 *
 * @author hassen
 */
public class SousCategorie {
    private int id ;
    private int categorie_id;
    private String libelle ;

    public SousCategorie() {
    }

    public SousCategorie(String libelle) {
        this.libelle = libelle;
    }

    public SousCategorie(int id,int categorie_id, String libelle) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.libelle = libelle;
    }
    

    public SousCategorie(int categorie_id,String libelle) {
        this.libelle = libelle;
        this.categorie_id=categorie_id;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "SousCategorie{" + "id=" + id + ", categorie_id=" + categorie_id + ", libelle=" + libelle + '}';
    }

    
    
    
    
}


