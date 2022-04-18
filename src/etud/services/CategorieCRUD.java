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

/**
 *
 * @author hassen
 */
public class CategorieCRUD {
    
    
    public void AjoutCategorie(Categorie c) {
        try {

            String query = " insert into Categorie (libelle)"
                    + " values (?)";
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, c.getLibelle());
            preparedStmt.execute();

            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
}
    
    public List<Categorie> AfficheCategorie() {
        List<Categorie> myList = new ArrayList<>();
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
                //System.out.println(g.getName() );
            }
        } catch (SQLException ex) {
        }
        return myList;
    }
    
    
    
    
    public void UpdateCategorie(Categorie c) {
        try {

            String query = " update Categorie set libelle = ? where id = ?; " ;
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, c.getLibelle());
            preparedStmt.setInt(2, c.getId());
            preparedStmt.execute();
            System.out.println("categorie updated ");
            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    
    public void DeleteCategorie(Categorie c) {
        try {

            String query = " delete from Categorie where id = ? ; " ;
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
             Myconnection conn = new Myconnection();
            conn.connect();
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
        
            preparedStmt.setInt(1, c.getId());
            preparedStmt.execute();
            System.out.println("Categorie deleted ");
            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
