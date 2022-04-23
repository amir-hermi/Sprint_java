/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.SousCategorie;
import etud.services.SousCategorieCRUD;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class DetailsSousCategorieController implements Initializable {

    @FXML
    private TextField sclibelle;
    @FXML
    private TableView<SousCategorie> tvsouscategorie;
    @FXML
    private TableColumn<SousCategorie, String> tvlibelle;
    @FXML
    private TextField tfid;
    @FXML
    private Button deletesousCategorie;
    @FXML
    private Button updatesousCategorie;
    @FXML
    private Button AjoutersousCategorie;
    @FXML
    private ComboBox<String> combocat;
    @FXML
    private TableColumn<SousCategorie, String> tvcategorie;
    
    ObservableList<SousCategorie> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        SousCategorieCRUD sc1 = new SousCategorieCRUD();
        combocat.setItems(sc1.GetListcategorie());
        refreshlist();
        tvsouscategorie.setOnMouseClicked( e -> {
        
        SousCategorie sc = new SousCategorie();
         sc = tvsouscategorie.getSelectionModel().getSelectedItem() ; 
            tfid.setText( String.valueOf( sc.getId())) ; 
            combocat.setValue( sc1.GetcategorieName( sc.getCategorie_id()   ) ); 
            
            sclibelle.setText(String.valueOf( sc.getLibelle()) ) ;
        });
      
    }

public void refreshlist(){
            SousCategorieCRUD sc = new SousCategorieCRUD();
             SousCategorie soucat = new  SousCategorie() ; 
            ObservableList< SousCategorie> list = sc.AfficheSousCategorie();
      
        
       
        tvlibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
        
        tvcategorie.setCellValueFactory( cellData -> new SimpleStringProperty( sc.GetcategorieName(cellData.getValue().getCategorie_id()) ) );
       
        tvsouscategorie.setItems(list) ; 
        
    }    
    
    @FXML
    private void AjoutsousCategorie(ActionEvent event) {
        
        
        SousCategorieCRUD sc1 = new SousCategorieCRUD() ; 
        
        
        if (sclibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
        }
        
        String libelle = sclibelle.getText();
        SousCategorieCRUD souscat = new SousCategorieCRUD();
        SousCategorie sc  = new SousCategorie ();
        sc.setLibelle(sclibelle.getText())   ;
        sc.setCategorie_id( souscat.GetcategorieId( combocat.getSelectionModel().getSelectedItem().toString()) );
        
         sc1.AjoutSousCategorie(sc);
         refreshlist();
         
         
    }
    
    
    
    
    @FXML
    private void updatesousCategorie(ActionEvent event) {
        
        
        int id = Integer.parseInt(tfid.getText()) ;
        SousCategorieCRUD sc1 = new SousCategorieCRUD() ; 
        
        
        if (sclibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
        }
        
        
        String libelle = sclibelle.getText();
        SousCategorieCRUD souscat = new SousCategorieCRUD();
        SousCategorie sc  = new SousCategorie ();
        sc.setId(id);
        sc.setLibelle(sclibelle.getText())   ;
        sc.setCategorie_id( souscat.GetcategorieId( combocat.getSelectionModel().getSelectedItem().toString()) );
        
         sc1.UpdateSousCategorie(sc);
         refreshlist();
    }
   
    @FXML
    private void deletesousCategorie(ActionEvent event) {
        SousCategorieCRUD sc1 = new SousCategorieCRUD() ; 
        SousCategorie sc = new SousCategorie() ; 
        
        int id = Integer.parseInt(tfid.getText()) ; 
        System.out.println(tfid.getText());
        sc.setId(id);
        sc1.DeleteSousCategorie(sc);
         refreshlist();
    }

    
    
    
}
