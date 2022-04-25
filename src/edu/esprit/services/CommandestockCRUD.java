/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Commandestock;
import edu.esprit.entities.CommandestockProduit;
import edu.esprit.entities.Fournisseur;
import edu.esprit.entities.Produit;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CommandestockCRUD {
    Connection cnx2;
    
    public CommandestockCRUD(){
    cnx2=MyConnection.getInstance().getCnx();
    }
    
    //premiére methode d'ajout
   public void ajoutCommandestock(Commandestock c , ArrayList<Produit> p) {
       LocalDate d = LocalDate.now();
       String requette = "INSERT INTO commandestock (date,etat,quantite,fournisseur_id)  VALUES" +"('"+String.valueOf(d)+"','En attente','"+c.getQuantite()+"','"+c.getFournisseur().getId()+"')";
         //String requette = "INSERT INTO commandestock (date,etat,quantite,fournisseur) VALUES ('"+c.getDate+"','"+c.get+"','"+c.getReference()+"','0','"+c.getUser().getId()+"'); ";
         String getCommReq ="SELECT * FROM commandestock  ORDER BY id desc LIMIT 1;";
         try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requette); //select nestaamlou executequery
            
            Statement getComm = cnx2.createStatement();
             ResultSet rs =getComm.executeQuery(getCommReq);
            
             while (rs.next()){
                 
                 CommandestockProduit cmp = new CommandestockProduit();
                 
                 for (Produit produits : p) {                                                                     
                          String insertCMP = "INSERT INTO commandestock_produit (produit_id,commandestock_id) VALUES ('"+produits.getId()+"','"+rs.getInt("id")+"'); ";                                                                   
                          Statement STinsertCMP = cnx2.createStatement();
                          STinsertCMP.executeUpdate(insertCMP);                                                  
                     }               
             }
             System.out.println("votre commandestock est ajoutée avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
  //suppression Commandestock
    public void supprimerCommandestock(Commandestock c){
         
         try {
            
            String requete5  = "DELETE FROM Commandestock WHERE id=?";
            PreparedStatement pstu =cnx2.prepareStatement(requete5);       
            pstu.setInt(1, c.getId());
            int rowsDeleted = pstu.executeUpdate();
           if (rowsDeleted > 0) {
    System.out.println("A commandestock was deleted successfully!");
} 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
      
    //accepter/refuser commandestock
     public void modifierCommandestock(Commandestock c){
        try {
            String requete = "UPDATE Commandestock SET etat= ? WHERE id= ?";
           // Statement st = cnx2.createStatement();
             PreparedStatement pstu =cnx2.prepareStatement(requete);    
           pstu.setString(1, c.getEtat());
           pstu.setInt(2, c.getId());
            pstu.execute();
            System.out.println("la Commandestock est modifiée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
     //affichage des commandestock
     public List<Commandestock> listCommandestock(){
                     List<Commandestock> myList = new ArrayList<>();

        try {
            String requette ="SELECT * FROM commandestock INNER JOIN Fournisseur ON fournisseur.id = commandestock.fournisseur_id;";
            Statement st = cnx2.createStatement();
             ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="SELECT * FROM commandestock_produit INNER JOIN produit ON produit.id=commandestock_produit.produit_id where commandestock_id="+rs.getInt("id")+";";
                 Statement stp = cnx2.createStatement();
                 ResultSet prod =stp.executeQuery(req);
             while (prod.next()){
                 Produit p = new Produit();
                 p.setNom(prod.getString("nom"));
                // p.setPrix(Float.valueOf(prod.getString("prix")) );
                // p.setTaille(prod.getString("taille"));
                 //p.setQantite(prod.getInt("quantite_produit"));
                 produits.add(p);
            } 
            
                Commandestock c = new Commandestock();
                Fournisseur u = new Fournisseur();
                u.setNom_fournisseur(rs.getString("Nom_fournisseur"));
                u.setAdresse_fournisseur(rs.getString("Adresse_fournisseur"));
                u.setMDP_fournisseur(rs.getString("MDP_fournisseur"));
                u.setLastname(rs.getString("lastname"));
                u.setTel(rs.getString("tel"));
                u.setId(rs.getInt("fournisseur_id"));
                
                c.setId(rs.getInt(1));                
                c.setDate(rs.getString("date"));
                c.setEtat(rs.getString("etat"));
                c.setQuantite(rs.getString("quantite"));
                c.setFournisseur(u);
               
                c.setProduit(produits);
               
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     return myList;
     }
     
     
     
     
}
