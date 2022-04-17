/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.pidev.entites.Reclamation;
import edu.pidev.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HistoriqueController implements Initializable {

    @FXML
    private Button Retour;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private TableColumn<Reclamation, String> colCateg;
    @FXML
    private TableColumn<Reclamation, String> colStatus;
    @FXML
    private TableView<Reclamation> tvReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherReclamation();
    }    

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("Mission.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HistoriqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public Connection getCnx()
    {
        Connection cnx;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/sporttech", "root", "");
            return cnx;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
                   }
    }
    
    public ObservableList<Reclamation> getReclamationList(){
     ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM reclamation";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Reclamation r;
       while(rs.next())
            {
          
                r = new Reclamation();
               
                r.setStatus(rs.getString("Status"));
                r.setCategorie_reclamation(rs.getString("categorie_reclamation"));
                r.setDate(rs.getString("date"));
                
            ReclamationList.add(r);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
         return ReclamationList;
    }
    
    
   public void afficherReclamation() {
      ObservableList<Reclamation> list = getReclamationList();
      colCateg.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("categorie_reclamation"));
      colDate.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
      colStatus.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Status"));
      
     
      
          tvReclamation.setItems(list);
         
    
    }
   
    private void Refresh()
    {
     ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM reclamation";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Reclamation r;
       while(rs.next())
            {
          
                r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setStatus(rs.getString("Status"));
                r.setCategorie_reclamation(rs.getString("categorie_reclamation"));
                r.setDate(rs.getString("date"));
                
            ReclamationList.add(r);
            tvReclamation.setItems(ReclamationList);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
        
    }
    
    
}
