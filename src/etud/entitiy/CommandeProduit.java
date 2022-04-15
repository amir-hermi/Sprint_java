/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.entitiy;

/**
 *
 * @author hp
 */
public class CommandeProduit {
    private int id;
    private int quantiteProduit;
    private Produit produit;
    private Commande commande;

    public CommandeProduit() {
    }

    public CommandeProduit(int quantiteProduit, Produit produit, Commande commande) {
        this.quantiteProduit = quantiteProduit;
        this.produit = produit;
        this.commande = commande;
    }

    public int getId() {
        return id;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
}
