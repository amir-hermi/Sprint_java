/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entites.Mission;
import edu.pidev.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MissionCRUD {
     Connection cnx2;

  public MissionCRUD(){
    cnx2 = MyConnection.getInstance().getCnx();
  }
   public void ajouterMission(Mission m){
        try {
            String requete2 = "INSERT INTO mission (status,adresse,livreur,utilisateur_id,date)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, m.getStatus());
            pst.setString(2, m.getAdresse());
            pst.setString(3, m.getLivreur());
            pst.setInt(4, m.getIdUser());
             pst.setString(5, String.valueOf(m.getDate()));
            pst.executeUpdate();
            System.out.println("la mission est bien affectée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
   
   public List<Mission> afficherMissions(){
         List<Mission> list = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM mission";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                Mission m = new Mission();
                m.setId(rs.getInt(1));
                m.setStatus(rs.getString("Status"));
                m.setAdresse(rs.getString("adresse"));
                m.setLivreur(rs.getString("livreur"));
                m.setIdUser(rs.getInt("utilisateur_id"));
                list.add(m);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
         return list;
    }
   public void supprimerMission(Mission m){
        try {
            String requete = "DELETE FROM mission";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("la mission est supprimée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
      public void modifierMission(int id,String status){
        try {
            String requete = "UPDATE mission SET status='"+status+"' WHERE id="+id+";";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("la mission est modifiée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());  
        }
    }
}
