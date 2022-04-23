/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Marque;
import etud.services.MarqueCRUD;
import java.net.URL;
import java.util.ResourceBundle;
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
public class DetailsMarqueController implements Initializable {

    @FXML
    private TextField mlibelle;
    @FXML
    private TableView<Marque> tvmarque;
    @FXML
    private TableColumn<Marque, String> tvlibelle;
    
    ObservableList<Marque> data=FXCollections.observableArrayList();
    
    @FXML
    private TextField tfid;
    @FXML
    private Button deletemarque;
    @FXML
    private Button updatemarque;
    @FXML
    private Button AjouterMarque;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        MarqueCRUD m1 = new MarqueCRUD();
        refreshlist();
        tvmarque.setOnMouseClicked( e -> {
        
        Marque m = new Marque();
         m = tvmarque.getSelectionModel().getSelectedItem() ; 
            tfid.setText( String.valueOf( m.getId())) ; 
            mlibelle.setText(String.valueOf( m.getLibelle()) ) ;
        
        
        });
        
        
        
    }   
    
    public void refreshlist(){
            MarqueCRUD m = new MarqueCRUD ();
            Marque mar = new Marque() ; 
            ObservableList<Marque> list =m.AfficheMarque();
           
        tvlibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
        tvmarque.setItems(list) ; 
        
    }

    @FXML
    private void AjoutMarque(ActionEvent event) {
        
        MarqueCRUD m1 = new MarqueCRUD() ; 
        
        
        if (mlibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
            
        }
        
        
        String libelle = mlibelle.getText();
        Marque m  = new Marque (libelle);
        MarqueCRUD cat = new MarqueCRUD();
         m1.AjoutMarque(m);
         refreshlist();
        
    }
    
    @FXML
    private void updatemarque(ActionEvent event) {
        
      MarqueCRUD m1  = new MarqueCRUD() ; 
      
      if (mlibelle.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une libelle ");
            alert.showAndWait();
            return;
            
        }
      
      
        String libelle = mlibelle.getText();
        int id = Integer.parseInt(tfid.getText()) ;
        Marque m = new Marque ( id ,libelle);
        MarqueCRUD mar = new MarqueCRUD();
         m1.UpdateMarque(m);
         refreshlist(); 
        
    }
    
    @FXML
    private void deletemarque(ActionEvent event) {
        
         MarqueCRUD m  = new MarqueCRUD (); 
         Marque mar = new Marque ();
         int id = Integer.parseInt(tfid.getText()) ; 
      
         mar.setId(id);
         m.DeleteMarque(mar);
         refreshlist();
        
        
    } 
}
