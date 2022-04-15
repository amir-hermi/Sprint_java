/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.entitiy;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Panier {
    private int id ;
    private Utilisateur utilisateur;
    private ArrayList<Produit> listPorduits; 

    public Panier() {
    }

    public Panier(ArrayList<Produit> listPorduits) {
        this.listPorduits = listPorduits;
    }

    public Panier(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.listPorduits = new ArrayList<>();
    }
    

    public int getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArrayList<Produit> getListPorduits() {
        return listPorduits;
    }

    public void setListPorduits(ArrayList<Produit> listPorduits) {
        this.listPorduits = listPorduits;
    }

    @Override
    public String toString() {
        return "Panier{" + " listPorduits=" + listPorduits + '}';
    }
    
    
}
