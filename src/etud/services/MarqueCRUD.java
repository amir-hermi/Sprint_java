/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entities.Marque;
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
public class MarqueCRUD {
    
    public void AjoutMarque(Marque m) {
        try {

            String query = " insert into marque (libelle)"
                    + " values (?)";
            Myconnection conn = new Myconnection();
            conn.connect();
            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, m.getLibelle());
            preparedStmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
    
 public ObservableList<Marque> AfficheMarque() {
        ObservableList<Marque> myList = FXCollections.observableArrayList();
        String query = "SELECT * FROM marque";
        Statement st;
        Myconnection conn = new Myconnection();
        conn.connect();
        try {
            st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Marque m = new Marque();
                m.setId(rs.getInt(1));
                m.setLibelle(rs.getString("Libelle"));
                myList.add(m);
             }
        } catch (SQLException ex) {
        }
        return myList;
    }  
    
    
    
    public void UpdateMarque(Marque m) {
        try {

            String query = " update marque set libelle = ? where id = ?; " ;
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            preparedStmt.setString(1, m.getLibelle());
            preparedStmt.setInt(2, m.getId());
            preparedStmt.execute();
            System.out.println("marque updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
     public void DeleteMarque(Marque m) {
        try {

            String query = " delete from marque where id = ? ; " ;
            Myconnection conn = new Myconnection();
            conn.connect();

            PreparedStatement preparedStmt = conn.getConn().prepareStatement(query);
            
            preparedStmt.setInt(1, m.getId());
            preparedStmt.execute();
            System.out.println("Marque deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    
    
    
}
