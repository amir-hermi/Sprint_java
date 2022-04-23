/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entities.Categorie;
import etud.utils.Myconnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hassen
 */
public class CategorieCRUD {
    
    
    public void AjoutCategorie(Categorie c) {
        try {

            String query = " insert into Categorie (libelle)"
                    + " values (?)";
            
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, c.getLibelle());
            preparedStmt.execute();

            System.out.println("categorie added ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
}
    
    public ObservableList<Categorie> AfficheCategorie() {
        ObservableList<Categorie> myList = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM categorie";
        Statement st;
        Myconnection conn = new Myconnection();
        conn.connect();
        try {
            st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setLibelle(rs.getString("Libelle"));
                myList.add(c);
                
            }
        } catch (SQLException ex) {
        }
        return myList;
    }

    
    public void UpdateCategorie(Categorie c) {
        try {

            String query = " update Categorie set libelle = ? where id = ?; " ;
            
            Myconnection conn = new Myconnection();
            conn.connect();
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, c.getLibelle());
            preparedStmt.setInt(2, c.getId());
            preparedStmt.execute();
            System.out.println("categorie updated ");   
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    public void DeleteCategorie(Categorie c) {
        try {

            String query = " delete from Categorie where id = ? ; " ;
            
             Myconnection conn = new Myconnection();
            conn.connect();
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
        
            preparedStmt.setInt(1, c.getId());
            preparedStmt.execute();
            System.out.println("Categorie deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
