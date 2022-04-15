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
public class PanierProduit {
    private Produit produit;
    private Panier panier;

    public PanierProduit() {
    }

    public PanierProduit(Produit produit, Panier panier) {
        this.produit = produit;
        this.panier = panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "PanierProduit{" + "produit=" + produit + ", panier=" + panier + '}';
    }
    
    
}
