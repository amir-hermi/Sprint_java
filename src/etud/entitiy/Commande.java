/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.entitiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hp
 */
public class Commande implements Serializable{
    private int id ;
    private String date_creation;
    private String status ,reference;
    private Float montant;
    private ArrayList<Produit> produits;
    private Utilisateur user;

    public Commande() {
        
    }

    public Commande(String date_creation, String status, String reference, Utilisateur user) {
        this.date_creation = date_creation;
        this.status = status;
        this.reference = reference;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commande{" + "date_creation=" + date_creation + ", status=" + status + ", reference=" + reference + ", montant=" + montant + ", produits=" + produits + ", user=" + user + '}';
    }
    
    
    
}
