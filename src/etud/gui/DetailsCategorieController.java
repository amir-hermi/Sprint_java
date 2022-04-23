/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Categorie;
import etud.entities.Produit;
import etud.services.CategorieCRUD;
import etud.services.ProduitCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class DetailsCategorieController implements Initializable {

    @FXML
    private Button AjouterCategorie;
    @FXML
    private Button updatecategorie;
    @FXML
    private Button deletecategorie;
    @FXML
    private TextField tfid;
    @FXML
    private TableView<Categorie> tvcategorie;
    @FXML
    private TableColumn<Categorie, String> tvlibelle;
    
    ObservableList<Categorie> data=FXCollections.observableArrayList();
    
    @FXML
    private TextField clibelle;
    
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        CategorieCRUD c1 = new CategorieCRUD();
        refreshlist();
        tvcategorie.setOnMouseClicked( e -> {
        
        Categorie c = new Categorie();
         c = tvcategorie.getSelectionModel().getSelectedItem() ; 
            tfid.setText( String.valueOf( c.getId())) ; 
            clibelle.setText(String.valueOf( c.getLibelle()) ) ;
        
        
        });
        }
    
    
    public void refreshlist(){
            CategorieCRUD c = new CategorieCRUD ();
            Categorie cat = new Categorie() ; 
            ObservableList<Categorie> list =c.AfficheCategorie();
           
        tvlibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
        tvcategorie.setItems(list) ; 
        
    }

   

    @FXML
    private void AjoutCategorie(ActionEvent event) {
        
        CategorieCRUD c1 = new CategorieCRUD() ; 
        
        if (clibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
            
        }
        
        
        String libelle = clibelle.getText();
        Categorie c = new Categorie (libelle);
        CategorieCRUD cat = new CategorieCRUD();
         cat.AjoutCategorie(c);
         refreshlist();
    }

    @FXML
    private void updatecategorie(ActionEvent event) {
     
        CategorieCRUD c1  = new CategorieCRUD() ; 
        
        
        if (clibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
            
        }
        
        
        String libelle = clibelle.getText();
        int id = Integer.parseInt(tfid.getText()) ;
        Categorie c = new Categorie ( id ,libelle);
        CategorieCRUD cat = new CategorieCRUD();
         cat.UpdateCategorie(c);
         refreshlist();
         
         
        
    }

    @FXML
    private void deletecategorie(ActionEvent event) {
        
         CategorieCRUD c  = new CategorieCRUD() ; 
         Categorie cat = new Categorie();
         int id = Integer.parseInt(tfid.getText()) ; 
      
         cat.setId(id);
         c.DeleteCategorie(cat);
         refreshlist();
         
    }
    

}
