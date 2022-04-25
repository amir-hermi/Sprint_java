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
public class Produit {
    private int id , qantite;
    private float prix;
    private String image , nom , taille;

    public Produit() {
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(int qantite, String image, String nom, String taille) {
        this.qantite = qantite;
        this.image = image;
        this.nom = nom;
        this.taille = taille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQantite() {
        return qantite;
    }

    public void setQantite(int qantite) {
        this.qantite = qantite;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", qantite=" + qantite + ", prix=" + prix + ", image=" + image + ", nom=" + nom + ", taille=" + taille + '}';
    }
}
