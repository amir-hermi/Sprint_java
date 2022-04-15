/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entitiy.Commande;
import etud.entitiy.CommandeProduit;
import etud.entitiy.Panier;
import etud.entitiy.Produit;
import etud.entitiy.Utilisateur;
import etud.utils.MyConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class CommandeService {
    
    
     public void ajoutCommande(Commande c) {
         String requette = "INSERT INTO commande (date_creation,status,reference,montant,utilisateur_id) VALUES ('"+c.getDate_creation()+"','"+c.getStatus()+"','"+c.getReference()+"','0','"+c.getUser().getId()+"'); ";
         String getCommReq ="SELECT * FROM commande where utilisateur_id ="+c.getUser().getId()+" ORDER BY id desc LIMIT 1;";
         try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(requette); //select nestaamlou executequery
            
            Statement getComm = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs =getComm.executeQuery(getCommReq);
             float montant=0;
             while (rs.next()){
                 
                 CommandeProduit cmp = new CommandeProduit();
                 PanierService ps =new PanierService();
                 for (Panier panier : ps.listPanier(1)) {
                     for (Produit p : panier.getListPorduits()) {
                         
                          montant = montant + (p.getPrix()*p.getQantite());
                          String insertCMP = "INSERT INTO commande_produit (produit_id,commande_id,quantite_produit) VALUES ('"+p.getId()+"','"+rs.getInt("id")+"',"+p.getQantite()+"); ";
                          String setQTEproduit = "UPDATE produit SET quantite=1 WHERE id="+p.getId()+";";
                          
                          
                          Statement STinsertCMP = MyConnexion.getInstance().getCnx().createStatement();
                          STinsertCMP.executeUpdate(insertCMP);
                          
                          Statement STsetQTEproduit = MyConnexion.getInstance().getCnx().createStatement();
                          STsetQTEproduit.executeUpdate(setQTEproduit);
                     }
                 }
                 String setMontantCommande = "UPDATE commande SET montant="+montant+" WHERE id="+rs.getInt("id")+";";
                 Statement STmontant = MyConnexion.getInstance().getCnx().createStatement();
                          STmontant.executeUpdate(setMontantCommande);
                ps.viderPanier(c.getUser().getId());
             }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
      public void UpdateCommande(int id , String newEtat) {
    String requette = "UPDATE commande SET status='"+newEtat+"' WHERE id="+id+";";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(requette); //select nestaamlou executequery
            System.out.println("Commande updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      public List<Commande> listCommande(){
                     List<Commande> myList = new ArrayList<>();

        try {
            String requette ="SELECT * FROM commande INNER JOIN utilisateur ON utilisateur.id = commande.utilisateur_id;";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="SELECT * FROM commande_produit INNER JOIN produit ON produit.id=commande_produit.produit_id where commande_id="+rs.getInt("id")+";";
                 Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                 ResultSet prod =stp.executeQuery(req);
             while (prod.next()){
                 Produit p = new Produit();
                 p.setNom(prod.getString("nom"));
                 p.setPrix(Float.valueOf(prod.getString("prix")) );
                 p.setTaille(prod.getString("taille"));
                 p.setQantite(prod.getInt("quantite_produit"));
                 produits.add(p);
             } 
                Commande c = new Commande();
                Utilisateur u = new Utilisateur();
                u.setEmail(rs.getString("email"));
                u.setEtat(rs.getString("etat"));
                u.setUsername(rs.getString("username"));
                u.setLastname(rs.getString("lastname"));
                u.setTel(rs.getString("tel"));
                c.setId(rs.getInt(1));
                c.setDate_creation(rs.getString("date_creation"));
                c.setStatus(rs.getString("status"));
                c.setMontant(rs.getFloat("montant"));
                c.setReference(rs.getString("reference"));
                c.setProduits(produits);
                c.setUser((u));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     return myList;
     }
      
       public List<Commande> listCommande(Utilisateur ut){
                     List<Commande> myList = new ArrayList<>();

        try {
            String requette ="SELECT * FROM commande INNER JOIN utilisateur ON utilisateur.id = commande.utilisateur_id where utilisateur_id ="+ut.getId()+";";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="SELECT * FROM commande_produit INNER JOIN produit ON produit.id=commande_produit.produit_id where commande_id="+rs.getInt("id")+";";
                 Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                 ResultSet prod =stp.executeQuery(req);
             while (prod.next()){
                 Produit p = new Produit();
                 p.setNom(prod.getString("nom"));
                 p.setPrix(Float.valueOf(prod.getString("prix")) );
                 p.setTaille(prod.getString("taille"));
                 p.setQantite(prod.getInt("quantite_produit"));
                 produits.add(p);
             } 
                Commande c = new Commande();
                Utilisateur u = new Utilisateur();
                u.setEmail(rs.getString("email"));
                u.setEtat(rs.getString("etat"));
                u.setUsername(rs.getString("username"));
                u.setLastname(rs.getString("lastname"));
                u.setTel(rs.getString("tel"));
                c.setId(rs.getInt(1));
                c.setDate_creation(rs.getString("date_creation"));
                c.setStatus(rs.getString("status"));
                c.setMontant(rs.getFloat("montant"));
                c.setReference(rs.getString("reference"));
                c.setProduits(produits);
                c.setUser((u));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     return myList;
     }
   
}
