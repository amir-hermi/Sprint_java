/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.test;

import etud.entitiy.Commande;
import etud.entitiy.Panier;
import etud.entitiy.Produit;
import etud.entitiy.Utilisateur;
import etud.services.ClientS;
import etud.services.CommandeService;
import etud.services.PanierService;
import etud.services.PaymentServiceImpl;
import etud.services.ServerS;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author hp
 */
public class MainClass {

    public static void main(String[] args) {
        CommandeService sc = new CommandeService();
        PanierService ps = new PanierService();
        Utilisateur u = new Utilisateur(1);
        // ServerS server = new ServerS();
        /**
         * *** Affiche tous les commandes **********
         */
        //System.out.println("/***** Affiche tous les commandes ***********/");
        // System.out.println(sc.listCommande()); 
        /**
         * *** Affiche la liste des produits dans le panier de l'utilisateur 1
         * **********
         */
        //System.out.println("/***** Affiche la liste des produits dans le panier de l'utilisateur 1 ***********/");
        //System.out.println(ps.listPanier(1)); 
        /**
         * *** modifier le status du commande **********
         */
        //sc.UpdateCommande(1, "Confirmée");

        /**
         * *** Ajout produit to panier **********
         */
        /* System.out.println("************ Panier avant ajout ***************");
        System.out.println(ps.listPanier(1));
        Produit p = new Produit(24);
        ps.remplirPanier(1, p);
        System.out.println("************ Panier apres ajout ***************");
        System.out.println(ps.listPanier(1)); */
        /**
         * Modifier produit
         */
        /* System.out.println("************  Panier AVANT UPDATE ***************");
        System.out.println(ps.listPanier(1));
        ps.UpdateProduit(19, 3, "L");
        System.out.println("************  Panier APRES APDATE ***************");
        System.out.println(ps.listPanier(1)); */
        /**
         * *** supprimer produit from panier **********
         */
        /* System.out.println("************ Panier avant suppression ***************");
        System.out.println(ps.listCommande());
        Produit p = new Produit(24);
        ps.deleteProduitFromPanier(1, p);
       
        
        System.out.println("************ Panier Panier apres suppression ***************");
        System.out.println(ps.listCommande());*/
        /**
         * *** Affiche les commandes de l'utilisateur u avant ajout **********
         */
        // System.out.println("/***** Affiche les commandes de l'utilisateur u ***********/");
        // System.out.println(sc.listCommande(u)); 
        /**
         * Creation Commande pour lutilisateur u
         */
        //String date = LocalDateTime.now().toString();
        //Commande c = new Commande(date , "En attente",String.valueOf(new Random().nextInt(9999999)),u);
        //sc.ajoutCommande(c);
        /**
         * *** Affiche les commandes de l'utilisateur u apres ajout **********
         */
        // System.out.println("/***** Affiche les commandes de l'utilisateur u apres ajout ***********/");
        // System.out.println(sc.listCommande(u)); 
        //System.out.println(ps.Recommandation(u)); 
        //server.run();
        //ClientS client = new ClientS();
        //sc.UpdateCommande(1, "En attente");
        //System.out.println(ps.afficheProduit());
        //System.out.println(sc.rechercheCommande("en"));
        //System.out.println(ps.nbProduit(1));
        Utilisateur user = new Utilisateur("mohamed", "mehdi", "ali@gmail.com", "12345678", "amiramir", "bloque");
        PaymentServiceImpl p = new PaymentServiceImpl();
// Create Stripe customer
        String id = p.createCustomer(user);

// Assign ID to user 
        user.setStripeCustomerId(id);
      //  System.out.println( p.checkVisa(id));
        //      System.out.println(user);
       // Commande order = new Commande();
       // order.setMontant(0.50f);
       // order.setUser(user);

        //p.chargeCreditCard(order);

    }
}
