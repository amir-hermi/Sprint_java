/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Fournisseur;
import edu.esprit.entities.Produit;
import edu.esprit.utils.MyConnection;
import java.sql.Statement;
import java.util.List;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 *
 * @author HP
 */
public class FournisseurCRUD {
    Connection cnx2;
    
    public FournisseurCRUD(){
    cnx2=MyConnection.getInstance().getCnx();
    }
    
    //premiére methode d'ajout
    public void ajouterFournisseur(Fournisseur f){
        try {
            String requete ="INSERT INTO fournisseur (nom_fournisseur,adresse_fournisseur,MDP_fournisseur,lastname,tel) VALUES" +"('"+f.getNom_fournisseur()+"','"+f.getAdresse_fournisseur()+"','"+f.getMDP_fournisseur()+"','"+f.getLastname()+"','"+f.getTel()+"')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Fournisseur ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //deuxiéme methode d'ajout
     public void ajouterFournisseur2(Fournisseur F){
        try {
            String requete2 = "INSERT INTO fournisseur (nom_fournisseur,adresse_fournisseur,MDP_fournisseur,lastname,tel)"+ "VALUES (?,?,?,?,?)";
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setString(1, F.getNom_fournisseur());
            pst.setString(2, F.getAdresse_fournisseur());
            pst.setString(3, F.getMDP_fournisseur());
            pst.setString(4, F.getLastname());
            pst.setString(5, F.getTel());
            pst.executeUpdate();
            System.out.println("votre Fournisseur est ajoutée avec succés");   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
     
     public List<Fournisseur> afficherFournisseur(){
         List<Fournisseur> myList = new ArrayList<>();
        try {
            //List<Fournisseur> myList = new ArrayList<>();
            String requete3 ="SELECT * FROM fournisseur";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt(1));
            fournisseur.setNom_fournisseur(rs.getString("nom_fournisseur"));
            fournisseur.setAdresse_fournisseur(rs.getString("adresse_fournisseur"));
            fournisseur.setMDP_fournisseur(rs.getString("MDP_fournisseur"));
            fournisseur.setLastname(rs.getString("lastname"));
            fournisseur.setTel(rs.getString("tel"));
            myList.add(fournisseur);          
            }                                
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
          return myList;
     }
     
     public List<Fournisseur> getFournisseur(String name){
         List<Fournisseur> myList = new ArrayList<>();
        try {
            //List<Fournisseur> myList = new ArrayList<>();
            String requete3 ="SELECT * FROM fournisseur where nom_fournisseur='"+name+"'";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt(1));
            fournisseur.setNom_fournisseur(rs.getString("nom_fournisseur"));
            fournisseur.setAdresse_fournisseur(rs.getString("adresse_fournisseur"));
            fournisseur.setMDP_fournisseur(rs.getString("MDP_fournisseur"));
            fournisseur.setLastname(rs.getString("lastname"));
            fournisseur.setTel(rs.getString("tel"));
            myList.add(fournisseur);          
            }                                
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
          return myList;
     }
      public List<Produit> getProduit(String name){
         List<Produit> myList = new ArrayList<>();
        try {
            //List<Fournisseur> myList = new ArrayList<>();
            String requete3 ="SELECT * FROM produit where nom='"+name+"'";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            Produit p = new Produit();
            p.setId(rs.getInt(1));
            myList.add(p);          
            }                                
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
          return myList;
     }
     public void modifierFournisseur(Fournisseur F){
        try {
            
            String requete4 = "UPDATE Fournisseur SET  nom_fournisseur=? ,adresse_fournisseur=?,"
                    + " mdp_fournisseur=?,lastname=?,tel=?  WHERE id=?";
            PreparedStatement pstu =cnx2.prepareStatement(requete4);
            pstu.setString(1, F.getNom_fournisseur());
            pstu.setString(2, F.getAdresse_fournisseur());
            pstu.setString(3, F.getMDP_fournisseur());
            pstu.setString(4, F.getLastname());
            pstu.setString(5, F.getTel());
            pstu.setInt(6, F.getId());
            int rowsUpdated = pstu.executeUpdate();
           if (rowsUpdated > 0) {
    System.out.println("An existing fournisseur was updated successfully!");
} 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
     
     public void supprimerFournisseur(Fournisseur F){
         
         try {
            
            String requete5  = "DELETE FROM Fournisseur WHERE id=?";
            PreparedStatement pstu =cnx2.prepareStatement(requete5);       
            pstu.setInt(1, F.getId());
            int rowsDeleted = pstu.executeUpdate();
           if (rowsDeleted > 0) {
    System.out.println("A fournisseur was deleted successfully!");
} 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
   
}
