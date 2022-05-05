/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.entitiy;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Utilisateur implements Serializable{
     private int id;
    private String username,lastname,email ,tel , password , etat;
    private Panier panier;
    private ArrayList<Commande>listCommande;
    private String stripeCustomerId;

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String lastname, String email, String tel, String password, String etat) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.etat = etat;
        this.listCommande = new ArrayList<>();
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String StripeCustomerId) {
        this.stripeCustomerId = StripeCustomerId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public ArrayList<Commande> getListCommande() {
        return listCommande;
    }

    public void setListCommande(ArrayList<Commande> listCommande) {
        this.listCommande = listCommande;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", lastname=" + lastname + ", email=" + email + ", tel=" + tel + ", password=" + password + ", etat=" + etat + ", panier=" + panier + ", listCommande=" + listCommande + ", StripeCustomerId=" + stripeCustomerId + '}';
    }

    
    
    
}
