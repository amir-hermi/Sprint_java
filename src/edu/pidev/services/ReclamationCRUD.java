/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entites.Reclamation;
import edu.pidev.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ReclamationCRUD {
    Connection cnx2;

  public ReclamationCRUD(){
    cnx2 = MyConnection.getInstance().getCnx();
  }
    
   
    public void ajouterReclamation(Reclamation r){
        try {
            String requete2 = "INSERT INTO reclamation (status,description,categorie_reclamation,utilisateur_id,date)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, r.getStatus());
            pst.setString(2, r.getDescription());
            pst.setString(3, r.getCategorie_reclamation());
            pst.setInt(4, r.getIdUser());
            pst.setString(5, r.getDate());
            pst.executeUpdate();
            System.out.println("Vottre reclamation est bien envoyée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
    public List<Reclamation> afficherReclamations(){
         List<Reclamation> list = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM reclamation";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setStatus(rs.getString("Status"));
                r.setDescription(rs.getString("Description"));
                r.setCategorie_reclamation(rs.getString("categorie_reclamation"));
                r.setIdUser(rs.getInt("utilisateur_id"));
                list.add(r);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
         return list;
    }
    
     public void supprimerReclamation(Reclamation r){
        try {
            String requete = "DELETE FROM reclamation";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("la reclamation est supprimée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
      public void modifierReclamation(int id){
        try {
            String requete = "UPDATE reclamation SET status='traité' WHERE id="+id+";";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("la reclamation est modifiée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
}
