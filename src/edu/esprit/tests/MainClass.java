/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Commandestock;
import edu.esprit.entities.Fournisseur;
import edu.esprit.entities.Produit;
import edu.esprit.services.CommandestockCRUD;
import edu.esprit.services.FournisseurCRUD;
import edu.esprit.utils.MyConnection;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection(); //etablir la connection 
        
        //test de singleton
            //MyConnection mc1 = MyConnection.getInstance(); 
           //MyConnection mc2 = MyConnection.getInstance(); 
          //System.out.println(mc1.hashCode()+ "-"+mc2.hashCode());
        
        
        
       
       
        //"l ajout d un fournisseur avec la 1ere méthode
            // Fournisseur F2= new Fournisseur("iheb","rue d'environnement","iheb","cherif","52200200");
            //FournisseurCRUD Fcd = new FournisseurCRUD();
          // Fcd.ajouterFournisseur2(F2);
        
        
        //test de la methode afficherFournisseur
            //FournisseurCRUD Fcd = new FournisseurCRUD();
          // System.out.println(Fcd.afficherFournisseur());
        
         
        //test de la methode UPDATE
            // Fournisseur f= new Fournisseur("hamza","farhat hached","khalil","mghirbi","93978047");
           //FournisseurCRUD Fcd = new FournisseurCRUD();
         // f.setId(1);
          //Fcd.modifierFournisseur(f);
         
         
          //test de la methode DELETE
             // Fournisseur f= new Fournisseur();
             //FournisseurCRUD Fcd = new FournisseurCRUD();
            //f.setId(1);
           //Fcd.supprimerFournisseur(f);
          
           
           
           
           //"l ajout d un comandestock avec la 1ere méthode
             //String date;
             //ArrayList<Produit> p = new ArrayList<>();
             //Produit pull = new  Produit(1);
             //p.add(pull);
             //Fournisseur f= new Fournisseur(1);
             //LocalDate d = LocalDate.now();
             //Commandestock F2= new  Commandestock(String.valueOf(d), "en attente", "200",f );
          // CommandestockCRUD ccd = new CommandestockCRUD();
          // ccd.ajoutCommandestock(F2, p);
           
           
           
           //test de la methode DELETE
             // Commandestock c2= new  Commandestock();
             //CommandestockCRUD ccdelete = new CommandestockCRUD();
            //c2.setId(4);
           //ccdelete.supprimerCommandestock(c2);
           
           
           //test de la methode modifiercommandestock:accepter/refuser
               //Commandestock c2= new  Commandestock();
              //CommandestockCRUD ccdelete = new CommandestockCRUD();
             //ccdelete.modifierCommandestock(1, "refuser");
           
           //test de la methode afficherFournisseur
            //CommandestockCRUD ccdelete = new CommandestockCRUD();
           //System.out.println(ccdelete.listCommandestock());
           
    }

}
