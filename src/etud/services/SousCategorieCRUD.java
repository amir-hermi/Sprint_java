/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entities.Categorie;
import etud.entities.SousCategorie;
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
public class SousCategorieCRUD {
    
    
    public void AjoutSousCategorie(SousCategorie sc) {
        try {

            String query = " insert into sous_categorie (categorie_id,libelle)"
                    + " values (?,?)";
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setInt(1, sc.getCategorie_id());
            preparedStmt.setString(2, sc.getLibelle());
            preparedStmt.execute();

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
}
    
    public ObservableList<SousCategorie> AfficheSousCategorie() {
        ObservableList<SousCategorie> myList = FXCollections.observableArrayList();
        String query = "SELECT * FROM sous_categorie";
        Statement st;
        Myconnection conn = new Myconnection();
        conn.connect();
        try {
            st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                SousCategorie sc = new SousCategorie();
                sc.setId(rs.getInt(1));
                sc.setCategorie_id((rs.getInt(2)) );
                sc.setLibelle(rs.getString("Libelle"));
                myList.add(sc);
             }
        } catch (SQLException ex) {
        }
        return myList;
    }


    
    
    
    
    public void UpdateSousCategorie(SousCategorie sc) {
        try {

            String query = " update sous_categorie set libelle = ? where id = ?; " ;
             Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, sc.getLibelle());
             preparedStmt.setInt(2, sc.getId());
            preparedStmt.execute();
            System.out.println("Souscategorie updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    
    public void DeleteSousCategorie(SousCategorie sc) {
        try {

            String query = " delete from sous_categorie where id = ? ; " ;
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
        
            preparedStmt.setInt(1, sc.getId());
            preparedStmt.execute();
            System.out.println("SousCategorie deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public int GetcategorieId(String name) {
        Myconnection conn = new Myconnection();
        int id = -1 ; 

        conn.connect();
        try {
            String query = " SELECT id from categorie where libelle = ? ; ";
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, name );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println(" cat not found "); 
                
            }
            else {
                id=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
        }
        finally{
            try {
                conn.getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id ; 
    }
    
    
    public String GetcategorieName(int id ) {
        Myconnection conn = new Myconnection();
        String name = "asdasdasdasd" ; 

        conn.connect();
        try {
            String query = " SELECT libelle from categorie where id = ? ; ";
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setInt(1, id );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println("sous cat not found "); 
                
            }
            else {
                    
                name=rs.getString(1);
            }
            
        } catch (SQLException ex) {
        }
        finally{
            try {
                conn.getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return name ; 
    }
    
    public ObservableList<String> GetListcategorie() {
        ObservableList<String> myList = FXCollections.observableArrayList();
        String query = "SELECT libelle FROM categorie";
        Statement st;
        Myconnection conn = new Myconnection();
        conn.connect();
        try {
            st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
             
                myList.add( rs.getString(1) );
            }
        } catch (SQLException ex) {
        }
        finally{
            try {
                conn.getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return myList;
    }
    
    

}
