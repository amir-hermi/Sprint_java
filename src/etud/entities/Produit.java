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

  public class Produit {

   
    private int  id;
    private float prix;
    private String image;
    private int quantite;
    private String taille;
    private String nom;
    private int marque;
    private int sousCategorie;
    private String description;

    public Produit() {
    }

    
    public Produit(int id, float prix, String image, int quantite, String taille, String nom, int marque, int sousCategorie,String description) {
        this.id = id;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.taille = taille;
        this.nom = nom;
        this.marque = marque;
        this.sousCategorie = sousCategorie;
        this.description = description;
    }

    public Produit(float prix, String image, int quantite, String taille, String nom, int marque, int sousCategorie , String description) {
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.taille = taille;
        this.nom = nom;
        this.marque = marque;
        this.sousCategorie = sousCategorie;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMarque() {
        return marque;
    }

    public void setMarque(int marque) {
        this.marque = marque;
    }

    public int getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategire(int sousCategorie) {
        this.sousCategorie = sousCategorie;
    }
    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", image=" + image + ", quantite=" + quantite + ", taille=" + taille + ", nom=" + nom + ", marque=" + marque + ", sousCategorie=" + sousCategorie + ", description=" + description + '}';
    }
    
    
}


