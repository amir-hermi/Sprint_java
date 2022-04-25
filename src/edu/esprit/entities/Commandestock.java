/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Commandestock {
    private int id;
    private String date;
    private String etat="en attente";
    private String quantite;
    Fournisseur fournisseur;
    ArrayList<Produit> produit;

    public Commandestock() {
    }

    public Commandestock(int id, String date, String etat, String quantite, Fournisseur fournisseur, ArrayList<Produit> produit) {
        this.id = id;
        this.date = date;
        this.etat = etat;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.produit = produit;
    }

    public Commandestock(  String quantite, Fournisseur fournisseur) {
        this.date = date;
        this.etat = etat;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
       // this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ArrayList<Produit> getProduit() {
        return produit;
    }

    public void setProduit(ArrayList<Produit> produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commandestock{" + "id=" + id + ", date=" + date + ", etat=" + etat + ", quantite=" + quantite + ", fournisseur=" + fournisseur + ", produit=" + produit + '}';
    }

    

    
    
    
}
