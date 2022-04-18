/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entities.Produit;
import etud.utils.Myconnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hassen
 */
public class ProduitCRUD {
    
    public void AjoutProduit(Produit p) {
        try {

            String query = " INSERT INTO produit (prix, image, quantite, taille, nom, marque_id ,sous_categire_id,description)"
                    + " values (?, ?, ?, ? ,? , ? , ? , ?)";
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setFloat(1, p.getPrix());
            preparedStmt.setString(2, p.getImage());
            preparedStmt.setInt(3, p.getQuantite());
            preparedStmt.setString(4, p.getTaille());
            preparedStmt.setString(5, p.getNom());
            preparedStmt.setInt(6, p.getMarque());
            preparedStmt.setInt(7, p.getSousCategorie());
            preparedStmt.setString(8, p.getdescription());

            preparedStmt.execute();

            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public List<Produit> AfficheProduit() {
        List<Produit> myList = new ArrayList<>();
        String query = "SELECT * FROM Produit";
        Statement st;
        Myconnection conn = new Myconnection();
        conn.connect();
        
        try {
            st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(query);
             
            while (rs.next()) {
                 
                Produit p = new Produit();
             
                p.setId(rs.getInt(1));
                p.setPrix(rs.getFloat("prix"));
                p.setImage(rs.getString("image"));
                p.setQuantite(rs.getInt("quantite"));
                p.setTaille(rs.getString("taille"));
                p.setNom(rs.getString("nom"));
                p.setMarque(rs.getInt("marque_id"));
                p.setSousCategire(rs.getInt("sous_categire_id"));
                p.setdescription(rs.getString("description"));
                System.out.println(p);
                myList.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return myList;
    }
    
    public void UpdateProduit(Produit p) {
        try {

            String query = " update produit set prix = ? , image = ? , quantite = ? , taille = ?, nom = ? , marque_id = ? , sous_categire_id = ? , description = ? where id = ? ; " ;
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            
            preparedStmt.setFloat(1, p.getPrix());
            preparedStmt.setString(2, p.getImage());
            preparedStmt.setInt(3, p.getQuantite());
            preparedStmt.setString(4, p.getTaille());
            preparedStmt.setString(5, p.getNom());
            preparedStmt.setInt(6, p.getMarque());
            preparedStmt.setInt(7, p.getSousCategorie());
            preparedStmt.setString(8, p.getdescription());
            preparedStmt.setInt(9, p.getId());
            preparedStmt.execute();
            System.out.println("produit updated ");
            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void DeleteProduit(Produit p) {
        try {

            String query = " delete from produit where id = ? ; " ;
            // drop matekhdemch fl prepared , prepred statment drequette dynamique
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
        
            preparedStmt.setInt(1, p.getId());
            preparedStmt.execute();
            System.out.println("produit deleted ");
            // System.out.println("guide added " + preparedStmt.executeUpdate(query));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
       
    }
    
    
    public int GetmarqueId(String name) {
        Myconnection conn = new Myconnection();
        int id = -1 ; 

        conn.connect();
        try {
            String query = " SELECT id from marque where libelle = ? ; ";
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, name );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println("marque not found "); 
                
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
    
    
    public String GetmarqueName(int id ) {
        Myconnection conn = new Myconnection();
        String name = "asdasdasdasd" ; 

        conn.connect();
        try {
            String query = " SELECT libelle from marque where id = ? ; ";
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setInt(1, id );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println("marque not found "); 
                
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
    
    public ObservableList<String> GetListmarque() {
        ObservableList<String> myList = FXCollections.observableArrayList();
        String query = "SELECT libelle FROM marque";
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
    
    
    public int GetsouscategorieId(String name) {
        Myconnection conn = new Myconnection();
        int id = -1 ; 

        conn.connect();
        try {
            String query = " SELECT id from sous_categorie where libelle = ? ; ";
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, name );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println("sous cat not found "); 
                
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
    
    
    public String GetsouscategorieName(int id ) {
        Myconnection conn = new Myconnection();
        String name = "asdasdasdasd" ; 

        conn.connect();
        try {
            String query = " SELECT libelle from sous_categorie where id = ? ; ";
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
    
    public ObservableList<String> GetListsouscategorie() {
        ObservableList<String> myList = FXCollections.observableArrayList();
        String query = "SELECT libelle FROM sous_categorie";
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